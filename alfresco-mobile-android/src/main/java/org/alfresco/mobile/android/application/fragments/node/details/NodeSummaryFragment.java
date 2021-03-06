/*******************************************************************************
 * Copyright (C) 2005-2014 Alfresco Software Limited.
 *
 * This file is part of Alfresco Mobile for Android.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.alfresco.mobile.android.application.fragments.node.details;

import java.util.Map;

import org.alfresco.mobile.android.api.constants.ContentModel;
import org.alfresco.mobile.android.api.model.Node;
import org.alfresco.mobile.android.application.R;
import org.alfresco.mobile.android.application.fragments.DisplayUtils;
import org.alfresco.mobile.android.application.managers.RenditionManagerImpl;
import org.alfresco.mobile.android.async.node.RetrieveNodeEvent;
import org.alfresco.mobile.android.async.node.delete.DeleteNodeEvent;
import org.alfresco.mobile.android.async.node.download.DownloadEvent;
import org.alfresco.mobile.android.async.node.favorite.FavoriteNodeEvent;
import org.alfresco.mobile.android.async.node.favorite.FavoritedNodeEvent;
import org.alfresco.mobile.android.async.node.like.LikeNodeEvent;
import org.alfresco.mobile.android.async.node.update.UpdateNodeEvent;
import org.alfresco.mobile.android.async.tag.TagsEvent;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.squareup.otto.Subscribe;

/**
 * Responsible to display details of a specific Node.
 * 
 * @author Jean Marie Pascal
 */
public class NodeSummaryFragment extends NodePropertiesFragment
{
    public static final String TAG = NodeSummaryFragment.class.getName();

    // //////////////////////////////////////////////////////////////////////
    // COSNTRUCTORS
    // //////////////////////////////////////////////////////////////////////
    public NodeSummaryFragment()
    {
        layoutId = R.layout.app_details_summary;
    }

    protected static NodeSummaryFragment newInstanceByTemplate(Bundle b)
    {
        NodeSummaryFragment bf = new NodeSummaryFragment();
        bf.setArguments(b);
        return bf;
    }

    // ///////////////////////////////////////////////////////////////////////////
    // CREATE PARTS
    // ///////////////////////////////////////////////////////////////////////////
    @Override
    protected void display(Node refreshedNode)
    {
        // Detect if restrictable
        isRestrictable = node.hasAspect(ContentModel.ASPECT_RESTRICTABLE);
        renditionManager = RenditionManagerImpl.getInstance(getActivity());

        displayData();
        if (!DisplayUtils.hasCentralPane(getActivity()))
        {
            displayPreview();
            displayHeader();
            displayToolsBar();
        }
        displayProperties();
    }

    // ///////////////////////////////////////////////////////////////////////////
    // EVENTS RECEIVER
    // ///////////////////////////////////////////////////////////////////////////
    @Subscribe
    public void onResult(RetrieveNodeEvent event)
    {
        super.onResult(event);
    }

    @Subscribe
    public void onLikeEvent(LikeNodeEvent event)
    {
        super.onLikeEvent(event);
    }

    @Subscribe
    public void onFavoriteEvent(FavoritedNodeEvent event)
    {
        super.onIsFavoriteEvent(event);
    }

    @Subscribe
    public void onFavoriteEvent(FavoriteNodeEvent event)
    {
        super.onFavoriteNodeEvent(event);
    }

    @Subscribe
    public void onDocumentUpdated(UpdateNodeEvent event)
    {
        super.onDocumentUpdated(event);
    }

    @Subscribe
    public void onDocumentDownloaded(DownloadEvent event)
    {
        super.onDocumentDownloaded(event);
    }

    @Subscribe
    public void onNodeDeleted(DeleteNodeEvent event)
    {
        super.onNodeDeleted(event);
    }

    @Subscribe
    public void onTagsEvent(TagsEvent event)
    {
        super.onTagsEvent(event);
    }

    // ///////////////////////////////////////////////////////////////////////////
    // BUILDER
    // ///////////////////////////////////////////////////////////////////////////
    public static Builder with(Activity activity)
    {
        return new Builder(activity);
    }

    public static class Builder extends NodePropertiesFragment.Builder
    {
        // ///////////////////////////////////////////////////////////////////////////
        // CONSTRUCTORS
        // ///////////////////////////////////////////////////////////////////////////
        public Builder(Activity activity)
        {
            super(activity);
        }

        public Builder(Activity appActivity, Map<String, Object> configuration)
        {
            super(appActivity, configuration);
        }

        // ///////////////////////////////////////////////////////////////////////////
        // CREATE FRAGMENT
        // ///////////////////////////////////////////////////////////////////////////
        protected Fragment createFragment(Bundle b)
        {
            return newInstanceByTemplate(b);
        }
    }
}
