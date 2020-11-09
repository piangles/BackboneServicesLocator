#!/bin/bash

#Gateway will always be the first to stop.
./gatewayService.sh stop

./pricingService.sh stop
./newsReporter.sh stop
./idService.sh stop
./msgService.sh stop
./prefsService.sh stop
./authService.sh stop
./profileService.sh stop
./configService.sh stop
./cryptoService.sh stop
./loggingService.sh stop
./sessionService.sh stop
