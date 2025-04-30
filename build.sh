#!/bin/bash
javac -cp champions-arena.jar -d dist -source 8 -target 8 $(find src -name "*.java") -Xlint:-options