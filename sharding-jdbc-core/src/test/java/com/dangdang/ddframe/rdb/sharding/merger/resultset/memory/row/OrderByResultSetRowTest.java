/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.merger.resultset.memory.row;

import com.dangdang.ddframe.rdb.sharding.constant.OrderType;
import com.dangdang.ddframe.rdb.sharding.merger.fixture.MergerTestUtil;
import com.dangdang.ddframe.rdb.sharding.merger.fixture.TestResultSetRow;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.context.OrderItem;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class OrderByResultSetRowTest {
    
    @Test
    public void assertCompareToForAsc() throws SQLException {
        OrderByResultSetRow orderByResultSetRow1 = new OrderByResultSetRow(
                createResultSet("order_1", "order_2", "other_1"), Arrays.asList(new OrderItem(1, OrderType.ASC), new OrderItem(2, OrderType.ASC)));
        OrderByResultSetRow orderByResultSetRow2 = new OrderByResultSetRow(
                createResultSet("order_3", "order_4", "other_2"), Arrays.asList(new OrderItem(1, OrderType.ASC), new OrderItem(2, OrderType.ASC)));
        assertTrue(orderByResultSetRow1.compareTo(orderByResultSetRow2) < 0);
    }
    
    @Test
    public void assertCompareToForDesc() throws SQLException {
        OrderByResultSetRow orderByResultSetRow1 = new OrderByResultSetRow(
                createResultSet("order_1", "order_2", "other_1"), Arrays.asList(new OrderItem(1, OrderType.DESC), new OrderItem(2, OrderType.DESC)));
        OrderByResultSetRow orderByResultSetRow2 = new OrderByResultSetRow(
                createResultSet("order_3", "order_4", "other_2"), Arrays.asList(new OrderItem(1, OrderType.DESC), new OrderItem(2, OrderType.DESC)));
        assertTrue(orderByResultSetRow1.compareTo(orderByResultSetRow2) > 0);
    }
    
    @Test
    public void assertCompareToWhenEqual() throws SQLException {
        OrderByResultSetRow orderByResultSetRow = new OrderByResultSetRow(
                createResultSet("order_1", "order_2", "other"), Arrays.asList(new OrderItem(1, OrderType.DESC), new OrderItem(2, OrderType.DESC)));
        assertThat(orderByResultSetRow.compareTo(orderByResultSetRow), is(0));
    }
    
    private ResultSet createResultSet(final Object... values) throws SQLException {
        return MergerTestUtil.mockResult(Arrays.asList("order_col_1", "order_col_2", "other_col"), Collections.<ResultSetRow>singletonList(new TestResultSetRow(values)));
    }
}
