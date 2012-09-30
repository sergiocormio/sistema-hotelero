package com.cdz.sh.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		TestCrudDao.class,
        TestCustomerDao.class,
        TestDocumentTypeDao.class,
        TestOccupationDao.class,
        TestRateDao.class,
        TestReservationFormDao.class,
        TestSeasonDao.class,
        TestServiceTypeDao.class
})
public class AllDaoTests {

}
