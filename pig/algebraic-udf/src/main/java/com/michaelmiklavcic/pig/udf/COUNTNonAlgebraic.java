/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.michaelmiklavcic.pig.udf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pig.Accumulator;
import org.apache.pig.Algebraic;
import org.apache.pig.EvalFunc;
import org.apache.pig.FuncSpec;
import org.apache.pig.PigException;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.apache.pig.impl.logicalLayer.FrontendException;
import org.apache.pig.impl.logicalLayer.schema.Schema;

/**
 * Shamelessly pulled from apache for demo purposes.
 * 
 * Generates the count of the number of values in a bag.  This count does not
 * include null values, and thus matches SQL semantics for COUNT(a) (where a is
 * field) but not for COUNT(*) (where * in SQL indicates all).
 * <p>
 * This class
 * implements {@link org.apache.pig.Algebraic}, so if possible the execution will
 * performed in a distributed fashion.
 * <p>
 * There are no restrictions as to the data types inside the bag to be counted.
 * <p>
 * COUNT implements the {@link org.apache.pig.Accumulator} interface as well.
 * While this will never be
 * the preferred method of usage it is available in case the combiner can not be
 * used for a given calculation.
 */
public class COUNTNonAlgebraic extends EvalFunc<Long> {
    private static TupleFactory mTupleFactory = TupleFactory.getInstance();

    @Override
    public Long exec(Tuple input) throws IOException {
        try {
            DataBag bag = (DataBag)input.get(0);
            if(bag==null)
                return null;

            Iterator it = bag.iterator();
            long cnt = 0;
            while (it.hasNext()){
                    Tuple t = (Tuple)it.next();
                    if (t != null && t.size() > 0 && t.get(0) != null )
                            cnt++;
            }
            return cnt;
        } catch (ExecException ee) {
            throw ee;
        } catch (Exception e) {
            int errCode = 2106;                
            String msg = "Error while computing count in " + this.getClass().getSimpleName();
            throw new ExecException(msg, errCode, PigException.BUG, e);
        }
    }

    @Override
    public Schema outputSchema(Schema input) {
        return new Schema(new Schema.FieldSchema(null, DataType.LONG)); 
    }
    
    /* (non-Javadoc)
     * @see org.apache.pig.EvalFunc#getArgToFuncMapping()
     */
    @Override
    public List<FuncSpec> getArgToFuncMapping() throws FrontendException {
        List<FuncSpec> funcList = new ArrayList<FuncSpec>();
        Schema s = new Schema();
        s.add(new Schema.FieldSchema(null, DataType.BAG));
        funcList.add(new FuncSpec(this.getClass().getName(), s));
        return funcList;
    }
    
}
