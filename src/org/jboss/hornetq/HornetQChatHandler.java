/*
* JBoss, Home of Professional Open Source.
* Copyright 2010, Red Hat, Inc., and individual contributors
* as indicated by the @author tags. See the copyright.txt file in the
* distribution for a full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.hornetq;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientConsumer;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;

import java.util.HashMap;

/**
 * @author <a href="mailto:andy.taylor@jboss.org">Andy Taylor</a>
 *         1/10/12
 */
public class HornetQChatHandler
{

   private ServerLocator locator;
   private ClientSession session;
   private ClientProducer producer;
   private ClientConsumer consumer;

   public void connect(String host, String port) throws Exception
   {
      HashMap<String, Object> params = new HashMap<String, Object>();
      params.put("host", host);
      params.put("port", port);
      TransportConfiguration tc = new TransportConfiguration(NettyConnectorFactory.class.getName(), params);
      locator = HornetQClient.createServerLocatorWithoutHA(tc);
      ClientSessionFactory clientSessionFactory = locator.createSessionFactory();
      session = clientSessionFactory.createSession(true, true);
      producer = session.createProducer("chatAddress");
      consumer = session.createConsumer("chatQueue");
   }
}
