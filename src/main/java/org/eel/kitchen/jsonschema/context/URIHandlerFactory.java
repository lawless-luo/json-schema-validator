/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.context;

import org.eel.kitchen.util.HTTPURIHandler;
import org.eel.kitchen.util.URIHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public final class URIHandlerFactory
{
    private final Map<String, URIHandler> schemeHandlers
        = new HashMap<String, URIHandler>();

    URIHandlerFactory()
    {
        schemeHandlers.put("http", new HTTPURIHandler());
    }

    public URIHandler getHandler(final URI uri)
    {
        final String scheme = uri.getScheme();

        if (scheme == null)
            throw new IllegalArgumentException("only absolute URIs are "
                + "supported");

        final URIHandler ret = schemeHandlers.get(scheme);

        if (ret == null)
            throw new IllegalArgumentException("unsupported scheme " + scheme);

        return ret;
    }
}
