package com.nickmirosh.podbook.utils


fun secondsToTimeString(seconds: Int) = "${seconds / 3600}:${seconds / 60}:${seconds % 60}"
