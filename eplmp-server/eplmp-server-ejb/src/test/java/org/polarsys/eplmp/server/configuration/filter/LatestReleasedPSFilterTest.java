/*******************************************************************************
 * Copyright (c) 2017-2019 DocDoku.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * DocDoku - initial API and implementation
 *******************************************************************************/

package org.polarsys.eplmp.server.configuration.filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.polarsys.eplmp.core.product.PartIteration;
import org.polarsys.eplmp.core.product.PartLink;
import org.polarsys.eplmp.core.product.PartMaster;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.polarsys.eplmp.server.util.ProductUtil.*;

@RunWith(MockitoJUnitRunner.class)
public class LatestReleasedPSFilterTest {

    private LatestReleasedPSFilter latestReleasedPSFilter;

    @Before
    public void setup() throws Exception {

        createTestableParts();
        latestReleasedPSFilter = new LatestReleasedPSFilter(false);
    }

    @Test
    public void filterTestWithPartMasterAsParameterTest(){

        PartMaster partMaster = getPartMasterWith("PART-001");
        String[] members = {};

        // released <=> true
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision B
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision C
        addRevisionWithPartLinkTo(partMaster, members, true, false);  // revision D

        List<PartIteration> result =  latestReleasedPSFilter.filter(partMaster);

        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        areThoseOfRevision("D", result, partMaster.getNumber());

        //--------------------------

        partMaster = getPartMasterWith("PART-002");
        partMaster.getLastRevision().release(user);
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision B
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision C
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision D

        result =  latestReleasedPSFilter.filter(partMaster);

        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        areThoseOfRevision("A", result, "PART-002");

        //--------------------------

        partMaster = getPartMasterWith("PART-003");
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision B
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision C
        addRevisionWithPartLinkTo(partMaster, members, false, false); // revision D

        result =  latestReleasedPSFilter.filter(partMaster);

        assertTrue(result.isEmpty());

        //--------------------------

        partMaster = getPartMasterWith("PART-004");
        partMaster.getLastRevision().release(user);
        addRevisionWithPartLinkTo(partMaster, members, true, false); // revision B
        addRevisionWithPartLinkTo(partMaster, members, true, false); // revision C
        addRevisionWithPartLinkTo(partMaster, members, false, false);// revision D

        result =  latestReleasedPSFilter.filter(partMaster);

        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        areThoseOfRevision("C", result, "PART-004");
    }

    @Test
    public void filterTestWithListPartLinkAsParameterTest(){

        generateSomeReleasedRevisionWithSubstitutesFor("PART-001");
        //diverge not enable
        PartMaster partMaster = getPartMasterWith("PART-001");

        List<PartLink> links =  new ArrayList<>((partMaster.getLastRevision().getLastIteration().getComponents()));
        List<PartLink> result  = latestReleasedPSFilter.filter(links);

        assertFalse(result.isEmpty());
        assertEquals(1,result.size());

        //enable diverge
        latestReleasedPSFilter = new LatestReleasedPSFilter(true);
        result  = latestReleasedPSFilter.filter(links);

        assertFalse(result.isEmpty());
        assertEquals(5,result.size());
        assertEquals("PART-016-UsageLink",result.get(0).getReferenceDescription());
        assertEquals("PART-018-Substitute",result.get(1).getReferenceDescription());
        assertEquals("PART-015-Substitute",result.get(2).getReferenceDescription());
        assertEquals("PART-009-Substitute",result.get(3).getReferenceDescription());
        assertEquals("PART-012-Substitute",result.get(4).getReferenceDescription());
    }
}