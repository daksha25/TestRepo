  import hudson.security.*
  import hudson.model.*

  
    def usertoAdd ="anamika"
    def password ="anamika"
    def job_name="aaa";
  	def user = [];
     
    //to check the user is available or not
    
    def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(com.cloudbees.plugins.credentials.common.StandardUsernameCredentials.class,Jenkins.instance,null,null);
  	for (c in creds) 
  	{
      def user1=c.username
      user.add(user1)
    }
      if(user.contains(usertoAdd))
       {
  //    println('user available')
  //add user to matrix and assign permissions
         
      AbstractProject proj = Hudson.instance.getItem(job_name)
      AuthorizationMatrixProperty authProperty = proj.getProperty(hudson.security.AuthorizationMatrixProperty)
      authProperty.add("hudson.model.Item.Build:anamika")
      authProperty.add("hudson.model.Item.Cancel:anamika")
      authProperty.add("hudson.model.Item.Configure:anamika")
      authProperty.add("hudson.model.Item.Move:anamika")
      authProperty.add("hudson.model.Item.Workspace:anamika")
      authProperty.add("com.cloudbees.plugins.credentials.CredentialsProvider.Delete:anamika");
  
    	Map<Permission, Set<String>> permissionMap = authProperty.getGrantedPermissions()
    
        proj.removeProperty(hudson.security.AuthorizationMatrixProperty)
        proj.addProperty(new AuthorizationMatrixProperty(permissionMap))
    
      
    
    }
   else
   {
     //user not available
 // println('not_available')
   def instance = Jenkins.getInstance()
  //create user
      def hudsonRealm = new HudsonPrivateSecurityRealm(false)
      hudsonRealm.createAccount(usertoAdd,password)
      instance.setSecurityRealm(hudsonRealm)
      instance.save()
  //add user to matrix and assign permissions
       	AbstractProject proj = Hudson.instance.getItem(job_name)
        AuthorizationMatrixProperty authProperty = proj.getProperty(hudson.security.AuthorizationMatrixProperty)
        authProperty.add("hudson.model.Item.Build:anamika")
        authProperty.add("hudson.model.Item.Cancel:anamika")
        authProperty.add("hudson.model.Item.Configure:anamika")
        authProperty.add("hudson.model.Item.Move:anamika")
        authProperty.add("hudson.model.Item.Workspace:anamika")
        authProperty.add("com.cloudbees.plugins.credentials.CredentialsProvider.Delete:anamika");
    
      Map<Permission, Set<String>> permissionMap = authProperty.getGrantedPermissions()
      
      proj.removeProperty(hudson.security.AuthorizationMatrixProperty)
      proj.addProperty(new AuthorizationMatrixProperty(permissionMap))
      
          println('done..')
   }
  
  
