package com.cdz.sh.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestBudgetService.class,
        TestCustomerServiceRetrieveAll.class,
        TestReservationFormService.class,
        /**
         * til now these ones have to be tested separately 
         */
        //TestCore_SameRoomType.class
        
})
public class AllServiceTests {

}
