package com.cdz.sh.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.cdz.sh.service.core.TestCore_SameRoomType;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestBudgetService.class,
        TestCustomerServiceRetrieveAll.class,
        TestReservationFormService.class,
        /**
         * CORE tests 
         */
        TestCore_SameRoomType.class
        
})
public class AllServiceTests {

}
