#!/bin/bash

./sessionService.sh start
sleep 10
./configService.sh start
sleep 10
./cryptoService.sh start
sleep 10
./loggingService.sh start
sleep 10
./profileService.sh start
sleep 10
./authService.sh start
sleep 10
./prefsService.sh start
sleep 10
./msgService.sh start
sleep 10
./idService.sh start
sleep 10
./newsReporter.sh start
sleep 10
#./pricingService.sh start
#sleep 10

#Gateway will always be the last to start.
sleep 10
./gatewayService.sh start
