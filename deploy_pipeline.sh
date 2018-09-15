#!/usr/bin/env bash

gradle run \
--args="--project= \
--pubsubSubscription= \
--downstreamGcs= \
--jobName= \
--runner=DataflowRunner \
--network= \
--subnetwork= \
--zone= \
--tempLocation= \
--stagingLocation= \
--filesToStage"
