#!/bin/bash

for xml in *.xml ;do xmllint --schema ../VEL.xsd $xml -noout; done

