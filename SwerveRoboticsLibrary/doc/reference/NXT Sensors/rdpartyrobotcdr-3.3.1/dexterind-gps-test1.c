#pragma config(Sensor, S1,     DGPS,                sensorI2CCustom)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

/*
 * $Id: dexterind-gps-test1.c 133 2013-03-10 15:15:38Z xander $
 */

/**
 * dexterind-gps.h provides an API for the Dexter Industries GPS Sensor.  This program
 * demonstrates how to use that API.
 *
 * Changelog:
 * - 0.1: Initial release
 * - 0.2: Removed common.h from includes
 *
 * Credits:
 * - Big thanks to Dexter Industries for providing me with the hardware necessary to write and test this.
 *
 * License: You may use this code as you wish, provided you give credit where it's due.
 *
 * THIS CODE WILL ONLY WORK WITH ROBOTC VERSION 3.59 AND HIGHER. 

 * Xander Soldaat (xander_at_botbench.com)
 * 20 February 2011
 * version 0.2
 */

#include "drivers/dexterind-gps.h"

task main () {

  long longitude = 0;
  long latitude = 0;
  long utc = 0;
  bool linkstatus = false;

  nxtDisplayCenteredTextLine(0, "Dexter Ind.");
  nxtDisplayCenteredBigTextLine(1, "GPS");
  nxtDisplayCenteredTextLine(3, "Test 1");
  nxtDisplayCenteredTextLine(5, "Connect sensor");
  nxtDisplayCenteredTextLine(6, "to S1");
  wait1Msec(2000);
  eraseDisplay();

  while (true) {
    // Read the sensor's data
    utc = DGPSreadUTC(DGPS);
    longitude = DGPSreadLongitude(DGPS);
    latitude = DGPSreadLatitude(DGPS);
    linkstatus = DGPSreadStatus(DGPS);

    nxtDisplayCenteredTextLine(0, "DGPS Test 1");
    nxtDrawLine(0, 52, 99, 52);
    nxtDisplayTextLine(2, "UTC: %d", utc);
    nxtDisplayTextLine(3, "Lon: %d", longitude);
    nxtDisplayTextLine(4, "Lat: %d", latitude);
    if (linkstatus)
      nxtDisplayTextLine(7, "Link Stat: [UP]");
    else
      nxtDisplayTextLine(7, "Link Stat: [DOWN]");
    nxtDrawLine(0, 20, 99, 20);
    wait1Msec(500);
  }
}

/*
 * $Id: dexterind-gps-test1.c 133 2013-03-10 15:15:38Z xander $
 */
