package fileSearcher

import org.scalatest.FlatSpec
import java.io.File


class MatcherTests extends FlatSpec{
    "Matcher that is passed a file matching the filter" should
    "return a list with that file name" in {
      val matcher = new Matcher("fake", "fakePath")
      val results = matcher.execute()
      assert(results == List("fakePath"))
    }

    "Matcher using a directory containing on file matching the filter" should
    "return a list with that file name" in {
      val matcher = new Matcher("txt", new File("./testFiles").getCanonicalPath())
      val results = matcher.execute()
      assert(results == List("readme.txt"))
    }

    "Matcher that is not passed a root file location" should
    "use the current location" in {
      val matcher = new Matcher("filter")
      assert(matcher.rootLocation == new File(".").getCanonicalPath())
    }

    "Matcher with sub folder checking matching a root location with two subtree files matching" should
    "return a list with those files names" in {
      val searchSubDirectories = true
      val matcher = new Matcher("txt", new File("./testFiles").getCanonicalPath(), searchSubDirectories)
      val results = matcher.execute()
      assert(results == List("notes.txt", "readme.txt"))
    }

}
