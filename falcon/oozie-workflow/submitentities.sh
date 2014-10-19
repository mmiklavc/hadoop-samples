#!/bin/bash

# SETUP FALCON
falcon entity -type cluster -file cluster.xml -submit
falcon entity -type feed -file feed-in.xml -submit
falcon entity -type feed -file feed-out.xml -submit
falcon entity -type process -file process-filter.xml -submit

