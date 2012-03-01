/*
 * Copyright (c) 2002-2012, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.archive.modules.rest.rs;

import fr.paris.lutece.plugins.archive.service.archive.IArchiveService;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 * ArchiveRest
 */
@Path( RestConstants.BASE_PATH + "archive" )
/**
 *
 * @author pierre
 */
public class ArchiveRest
{
    private IArchiveService archiveService;

    @POST
    @Path( "generateArchive" )
    @Produces( MediaType.TEXT_PLAIN )
    public String generateArchive( @FormParam( "folder_to_archive" )
    String strFolderToArchive, @FormParam( "archive_destination" )
    String strArchiveDestination, @FormParam( "archive_name" )
    String strArchiveName, @FormParam( "archive_type" )
    String strArchiveType )
    {
        return Integer.toString( archiveService.generateArchive( strFolderToArchive, strArchiveDestination,
                strArchiveName, strArchiveType ) );
    }

    @POST
    @Path( "informationArchive" )
    @Produces( MediaType.TEXT_PLAIN )
    public String informationArchive(@FormParam( "archive_item_key" )
    String archiveItemKey )
    {
        {
            int nArchiveItemKey = -1;

            try
            {
                nArchiveItemKey = Integer.parseInt( archiveItemKey );
            }
            catch ( NumberFormatException e )
            {
                AppLogService.error( e );
            }

            return archiveService.informationArchive( nArchiveItemKey );
        }
    }

    @POST
    @Path( "removeArchive" )
    @Produces( MediaType.TEXT_PLAIN )
    public void removeArchive( @FormParam( "archive_item_key" )
    String archiveItemKey )
    {
        {
            int nArchiveItemKey = -1;

            try
            {
                nArchiveItemKey = Integer.parseInt( archiveItemKey );
            }
            catch ( NumberFormatException e )
            {
                AppLogService.error( e );
            }

            archiveService.removeArchive( nArchiveItemKey );
        }
    }

    public void setArchiveService( IArchiveService archiveService )
    {
        this.archiveService = archiveService;
    }
}
