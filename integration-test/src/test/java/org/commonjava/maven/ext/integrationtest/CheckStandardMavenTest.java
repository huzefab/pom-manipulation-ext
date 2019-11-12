/*
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
package org.commonjava.maven.ext.integrationtest;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.commonjava.maven.ext.integrationtest.TestUtils.DEFAULT_MVN_PARAMS;
import static org.commonjava.maven.ext.integrationtest.TestUtils.runMaven;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckStandardMavenTest
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void verifyMavenWithExtensions()
        throws Exception
    {
        File tmpFolder = folder.newFolder();

        runMaven( "-X install", DEFAULT_MVN_PARAMS, tmpFolder.toString() );

        File[] files = tmpFolder.listFiles();

        assertTrue( files.length == 1 );
        assertTrue( "build.log".equals( files[0].getName() ) );

        String contents = FileUtils.readFileToString( files[0], StandardCharsets.UTF_8 );

        assertFalse( "Native Maven should not have PME installed!",
                     contents.contains( "Manipulation engine disabled. No project found." ) );
    }
}