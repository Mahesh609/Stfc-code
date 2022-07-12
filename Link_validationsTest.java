package seo_task_test;

import org.testng.annotations.Test;

import seo_task.Link_validations;

public class Link_validationsTest extends Link_validations {
  @Test
  public void brokenLinkTest() {
	  verifyLink();
  }
}
