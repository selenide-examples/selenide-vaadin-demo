package org.selenide;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class LocalHttpServer {

  private Server server;

  public LocalHttpServer(int port) {
    server = new Server(port);
  }

  public LocalHttpServer start() throws Exception {
    WebAppContext webapp = new WebAppContext();
    webapp.setContextPath("/");
    webapp.setResourceBase("src/main/webapp");
    webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
    server.setHandler(webapp);

    server.start();
    return this;
  }

  public void stop() throws Exception {
    if (server != null) server.stop();
    server = null;
  }

  private Set<String> sessions = new ConcurrentSkipListSet<String>();

  public static void main(String[] args) throws Exception {
    new LocalHttpServer(8080).start();
  }
}