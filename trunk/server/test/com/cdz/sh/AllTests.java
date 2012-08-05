package com.cdz.sh;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cdz.sh.dao.AllDaoTests;
import com.cdz.sh.mail.TestSendMail;
import com.cdz.sh.migration.TestMigrationService;
import com.cdz.sh.report.TestCustomersReport;
import com.cdz.sh.service.AllServiceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	// DAOs
	AllDaoTests.class,
    // Services
	AllServiceTests.class,
    // Mail
    TestSendMail.class,
    // Migration
    TestMigrationService.class,
    // Report
    TestCustomersReport.class
})
public class AllTests {

}
