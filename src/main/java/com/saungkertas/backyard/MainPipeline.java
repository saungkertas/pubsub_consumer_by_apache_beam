package com.saungkertas.backyard;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class MainPipeline {

    public static void main(String[] args) {

        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();
        Pipeline p = Pipeline.create(options);

        p.apply("Read Booking from Pubsub", PubsubIO.readStrings()
                .fromSubscription("your_pubsub_subscription")
                .withTimestampAttribute("timestamp_value"))
                .apply(TextIO.write().to("your_downstream")
                        .withoutSharding()
                        .withSuffix(".txt")
                        .withWindowedWrites());
    }
}
