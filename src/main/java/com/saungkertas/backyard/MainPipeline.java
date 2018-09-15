package com.saungkertas.backyard;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.windowing.AfterWatermark;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.joda.time.Duration;

public class MainPipeline {

    public static void main(String[] args) {

        Window<String> window = Window.<String>into(FixedWindows.of(Duration.standardMinutes(1)))
                .triggering(AfterWatermark.pastEndOfWindow())
                .discardingFiredPanes()
                .withAllowedLateness(Duration.standardMinutes(5));

        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline p = Pipeline.create(options);

        p.apply("Read Booking from Pubsub", PubsubIO.readStrings()
                .fromSubscription("your_pubsub_subscription")
                .withTimestampAttribute("timestamp_value"))
                .apply(window)
                .apply(TextIO.write().to("your_downstream")
                        .withoutSharding()
                        .withSuffix(".txt")
                        .withWindowedWrites());
    }
}
