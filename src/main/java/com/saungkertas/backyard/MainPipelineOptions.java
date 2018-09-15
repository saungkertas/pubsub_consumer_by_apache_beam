package com.saungkertas.backyard;

import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;
import org.apache.beam.sdk.options.ValueProvider;

public interface MainPipelineOptions extends PipelineOptions {

    @Description("Pubsub Subscription")
    @Validation.Required
    ValueProvider<String> getPubsubSubscription();

    void setPubsubSubscription(ValueProvider<String> pubsubSubscription);

    @Description("DownStream GCS")
    @Validation.Required
    ValueProvider<String> getDownstreamGcs();

    void setDownstreamGcs(ValueProvider<String> downstreamGcs);
}
