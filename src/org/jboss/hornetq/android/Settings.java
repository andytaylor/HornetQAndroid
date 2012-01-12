package org.jboss.hornetq.android;

public class Settings
{
   private long id;

   private String username;

   private String password;

   private String host;

   private String port;

   public Settings(long id, String username, String password, String host, String port)
   {
      this.id = id;
      this.username = username;
      this.password = password;
      this.host = host;
      this.port = port;
   }

   public long getId()
   {
      return id;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getHost()
   {
      return host;
   }

   public void setHost(String host)
   {
      this.host = host;
   }

   public String getPort()
   {
      return port;
   }
}
