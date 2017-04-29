package com.sight.facialrecog.JavaFaces;

/** ImageDistanceInfo.java
http://code.google.com/p/javafaces/
Modified by Benjamin Senyonyi and Ampamya Sight, March 2014
*/


public class ImageDistanceInfo
{
  private int index;
  private double value;

  public ImageDistanceInfo(double val, int idx)
  { value = val;
    index = idx;
  }

  public int getIndex()
  { return index; }

  public double getValue()
  { return value;  }

} // end of ImageDistanceInfo class

