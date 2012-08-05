package com.cdz.sh;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cdz.sh.dao.AllDaoTests;
import com.cdz.sh.dao.TestCustomerDao;
import com.cdz.sh.dao.TestDocumentTypeDao;
import com.cdz.sh.dao.TestOccupationDao;
import com.cdz.sh.dao.TestRateDao;
import com.cdz.sh.dao.TestReservationFormDao;
import com.cdz.sh.dao.TestSeasonDao;
import com.cdz.sh.dao.TestServiceTypeDao;
import com.cdz.sh.mail.TestSendMail;
import com.cdz.sh.migration.TestMigrationService;
import com.cdz.sh.report.TestCustomersReport;
import com.cdz.sh.service.AllServiceTests;
import com.cdz.sh.service.TestBudgetService;
import com.cdz.sh.service.TestCustomerServiceRetrieveAll;
import com.cdz.sh.service.TestReservationFormService;

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
