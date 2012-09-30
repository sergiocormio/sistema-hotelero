package com.cdz.sh.mail;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		TestSendMailFromGMail.class,
        TestSendMailFromYahoo.class
})
public class AllEMailTests {

}
