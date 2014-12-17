import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.JSONException;

/*
        Name: Joanna Ramberg
        This is a sample of my using JSON and HttpComponent Client.  This needs the HTTP Component
        Client package to compile/work.  You will also need the app id for Groupon in order to
        use the Groupon API.
*/

public class YelpAPISample {

	public static void main(String[] args) {
    	String output;
		URI uri = null;
		HttpClient httpclient = new DefaultHttpClient();
		JSONObject jsonobj = null;
		
		URIBuilder builder = new URIBuilder();
		String yelpClientId = "Please Replace this with your Yelp Client ID";

		builder.setScheme("https").setHost("api.groupon.com").setPath("/v2/deals")
	    .setParameter("division_id", "san-francisco")
	    .setParameter("area", "san-francisco")
	    .setParameter("client_id", yelpClientID);
		try {
			uri = builder.build();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
     
		if (entity != null) {
		    InputStream instream = null;

			try {
				instream = entity.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    } finally {
		        try {
		        	BufferedReader br = new BufferedReader(new InputStreamReader(instream));
		        	//System.out.println("Output from Server .... \n");
		        	while ((output = br.readLine()) != null) {
		        		//System.out.println(output);
			    		try {
			    			JSONTokener token = new JSONTokener(output);
			    			jsonobj = new JSONObject(token);
							JSONArray jsondealsarray = jsonobj.getJSONArray("deals");

							for (int i=0; i < jsondealsarray.length(); i++)
							{
								JSONObject jsondealobject = jsondealsarray.getJSONObject(i);
								System.out.println("Announcement Title: " + jsondealobject.getString("announcementTitle")+ "\n" + "Deal URL: " + jsondealobject.getString("dealUrl"));
							}
			    			
			    		} catch (JSONException e1) {
			    			// TODO Auto-generated catch block
			    			e1.printStackTrace();
			    		}
		        	}

		        	instream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		    }
		}
		

		System.out.println(httpget.getURI());
		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
	}

}
