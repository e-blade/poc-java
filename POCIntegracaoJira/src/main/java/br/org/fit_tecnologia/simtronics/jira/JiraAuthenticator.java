package br.org.fit_tecnologia.simtronics.jira;

import java.net.URI;
import java.util.Map;

import com.atlassian.jira.rest.client.AuthenticationHandler;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.ChangelogGroup;
import com.atlassian.jira.rest.client.domain.Comment;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.domain.input.FieldInput;
import com.atlassian.jira.rest.client.domain.input.IssueInput;
import com.atlassian.jira.rest.client.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

public class JiraAuthenticator {

	public static void main(String[] args) throws Exception {
        
		/*
		String strUrl = "https://fittest.atlassian.net/";
		String strUser = "fit";
		String strPass = "fittest";
		*/
		
		String strUrl = "https://testesinctronics.atlassian.net";
		String strUser = "augusto.sinctronics";
		String strPass = "FitFit@0!4";
		
		URI uri = new URI(strUrl);
		// Do NOT use it in with unencrypted HTTP protocol over public networks, as credentials are passed effectively in free text
		AuthenticationHandler authHandler = new BasicHttpAuthenticationHandler(strUser, strPass);
		
		JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();    					
        JiraRestClient client = factory.create(uri, authHandler);        

        // Invoke the JRJC Client
        Promise<User> promiseUser = client.getUserClient().getUser(strUser);
        User user = promiseUser.claim();
        
        // Print the result
        System.out.println(String.format("Your admin user's email address is: %s\r\n", user.getEmailAddress()));
        
        for (String group : user.getGroups().getItems()) {
			System.out.println(group);
		}
        
        Promise<Issue> p = client.getIssueClient().getIssue("TB-10731");
        Issue i = p.get();
        
        System.out.println("**************************************");
        System.out.println("Summary: " + i.getSummary());   
        System.out.println("Issue Type: " + i.getIssueType().getName());   
        System.out.println("Status: " + i.getStatus().getName());
        System.out.println("Description: " + i.getDescription());        
        
        // 2. Create new Issue
        IssueInputBuilder issueBuilder = new IssueInputBuilder("TB", 16L, "Inserido para teste!");
                
        issueBuilder.setDescription("Incluso pelo Client Java");
        // issueBuilder.setSummary("Inserido para teste!");
        
        issueBuilder.setFieldValue("10304", "18051-230");
        issueBuilder.setFieldValue("10400", "12.211.975/0001-48");
        issueBuilder.setFieldValue("10800", "Sorocaba");        
        
        //create the issue
        IssueInput issueInput = issueBuilder.build();
        Promise<BasicIssue> createdIssue = client.getIssueClient().createIssue(issueInput);
        BasicIssue basicIssue = createdIssue.get();
        System.out.println(basicIssue.getKey());        
        
        // 3. Done
        System.out.println("Example complete. Now exiting.");
        System.exit(0);
        
        /*
        //create the client
        BasicHttpAuthenticationHandler myAuthenticationHandler = new BasicHttpAuthenticationHandler("jira.username", "jira.password");
        JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
        URI jiraServerUri = new URI("http//jira.yourdomain.com");
        JiraRestClient restClient = factory.create(jiraServerUri, myAuthenticationHandler);

        //create the issue
        NullProgressMonitor pm = new NullProgressMonitor();
        com.atlassian.jira.rest.client.domain.input.IssueInput issueInput = issueBuilder.build();
        BasicIssue bIssue = restClient.getIssueClient().createIssue(issueInput, pm);

        //get the newly created issue
        com.atlassian.jira.rest.client.domain.Issue jIssue = restClient.getIssueClient().getIssue(bIssue.getKey(), pm);
        */
                              
        /*
        Map<String, FieldInput> fields;
        fields.put("1", new FieldInput("id", ""));
        fields.put("2", new FieldInput("id", ""));
        fields.put("3", new FieldInput("id", ""));               
               
        FieldInput[] fields;        
        fields[0] = new FieldInput("id", "");        
        issue.createWithFields(fields);        
        IssueInput issueInput = new IssueInput(fields);       
        issue = issue.createWithFields(fields);        
        client.getIssueClient().createIssue(issue);
        */
	}
	
	
}
