/**
 * Copyright (C) 2012 Red Hat, Inc. (jcasey@redhat.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.maven.ext.manip.util;

import org.commonjava.maven.ext.manip.fixture.TestUtils;
import org.commonjava.maven.ext.manip.io.PomIO;
import org.commonjava.maven.ext.manip.model.Project;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProjectInheritanceTest
{
    private static final String RESOURCE_BASE = "properties/";

    @Test
    public void testVerifyInheritance() throws Exception
    {
        // Locate the PME project pom file. Use that to verify inheritance tracking.
        final File projectroot = new File (TestUtils.resolveFileResource( RESOURCE_BASE, "" )
                                          .getParentFile()
                                          .getParentFile()
                                          .getParentFile()
                                          .getParentFile(), "pom.xml" );
        PomIO pomIO = new PomIO();
        List<Project> projects = pomIO.parseProject( projectroot );
        for ( Project p : projects )
        {
            System.out.println( "### p parent " + p.getProjectParent() );
            if ( ! p.getPom().equals( projectroot ) )
            {
                assertTrue ( p.getProjectParent().getPom().equals( projectroot ) );
            }
        }
    }
}
