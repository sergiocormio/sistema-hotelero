package com.cdz.sh;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cdz.sh.concurrence.TestConcurrency;
import com.cdz.sh.dao.AllDaoTests;
import com.cdz.sh.mail.AllEMailTests;
import com.cdz.sh.migration.TestMigrationServiceZipUtil;
import com.cdz.sh.report.TestCustomersReport;
import com.cdz.sh.service.AllServiceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	// DAOs
	AllDaoTests.class,
    // Services
	AllServiceTests.class,
    // Mail
    AllEMailTests.class,
    // Migration
    TestMigrationServiceZipUtil.class,
    // Report
    TestCustomersReport.class,
    // concurrency validation
    TestConcurrency.class
})
public class AllTests {

}
