package com.sight.facialrecog.JavaFaces;

/**  MatchResult.java
 http://code.google.com/p/javafaces/
 Modified by Sight and Benjamin March 2014
**/


public class MatchResult
{
  private String matchFnm;
  private double matchDist;


  public MatchResult(String fnm, double dist)
  { matchFnm = fnm;
    matchDist = dist;
  }

  public String getMatchFileName()
  { return matchFnm;  }

  public void setMatchFileName(String fnm)
  { matchFnm = fnm; }

  public double getMatchDistance()
  {  return matchDist;  }

  public void setMatchDistance(double dist)
  {  matchDist = dist;  }


  public String getName()
  // matchFnm is something like "saveFaces\andrew_0.png"; return "andrew"
  {
    int slashPos = matchFnm.lastIndexOf('\\');
    int extPos = matchFnm.lastIndexOf(".png");
    String name = (slashPos == -1) ? matchFnm.substring(0, extPos) : 
                                         matchFnm.substring(slashPos+1, extPos);

    name = name.replaceAll("_[0-9]*$", "");   // remove trailing numbers, etc
    return name;
  }  // end of getName()


}  // end of MatchResult class
