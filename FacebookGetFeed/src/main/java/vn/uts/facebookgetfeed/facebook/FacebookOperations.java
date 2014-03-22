package vn.uts.facebookgetfeed.facebook;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.api.impl.json.FacebookModule;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.MultiValueMap;

import vn.uts.facebookgetfeed.map.MultivaluedHashMapString;
import vn.uts.facebookgetfeed.object.FacebookPost;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class FacebookOperations {

	// private String accessToken;

	private Facebook facebook;
	private FacebookProfile me;
	private ObjectMapper objectMapper;

	public FacebookOperations(String accessToken) {
		super();
		// this.accessToken = accessToken;
		setAccessToken(accessToken);
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new FacebookModule());
	}

	public void setAccessToken(String accessToken) {
		@SuppressWarnings("unused")
		List<FacebookPost> posts;
		try {
			// attempt to use this accessToken
			Facebook fb = new FacebookTemplate(accessToken);
			FacebookProfile me = fb.userOperations().getUserProfile();
			// if success then assign...
			this.facebook = fb;
			this.me = me;
			// this.accessToken = accessToken;
		} catch (Exception e) {
			// else print error
			System.out.println(e.getMessage());
		}
	}

	public FacebookProfile getMe() {
		return me;
	}

	public List<FacebookPost> getHomeFeed(PagingParameters pp,
			MultiValueMap<String, String> queryParams) {
		return facebook.fetchConnections("me", "home", FacebookPost.class,
				queryParams);
	}

	@SuppressWarnings("unused")
	private <T> List<T> deserializeList(JsonNode jsonNode, String postType,
			Class<T> type) {
		JsonNode dataNode = jsonNode.get("data");
		List<T> posts = new ArrayList<T>();
		for (Iterator<JsonNode> iterator = dataNode.iterator(); iterator
				.hasNext();) {
			posts.add(deserializePost(postType, type,
					(ObjectNode) iterator.next()));
		}
		if (jsonNode.has("paging")) {
			JsonNode pagingNode = jsonNode.get("paging");
			PagingParameters previousPage = PagedListUtils
					.getPagedListParameters(pagingNode, "previous");
			PagingParameters nextPage = PagedListUtils.getPagedListParameters(
					pagingNode, "next");
			return new PagedList<T>(posts, previousPage, nextPage);
		}

		return new PagedList<T>(posts, null, null);
	}

	private <T> T deserializePost(String postType, Class<T> type,
			ObjectNode node) {
		try {
			if (postType == null) {
				postType = determinePostType(node);
			}

			node.put("postType", postType);
			node.put("type", postType);
			return objectMapper.reader(type).readValue(node.toString());
		} catch (Exception shouldntHappen) {
			System.out.println("Error deserializing " + postType + " post - "
					+ shouldntHappen.getMessage());
			return null;
		}
	}

	private String determinePostType(ObjectNode node) {
		if (node.has("type")) {
			try {
				String type = node.get("type").textValue();
				Post.PostType.valueOf(type.toUpperCase());
				return type;
			} catch (IllegalArgumentException e) {
				return "post";
			}
		}
		return "post";
	}

	private URIBuilder appendPagedListParameters(
			PagingParameters pagedListParameters, URIBuilder uriBuilder) {
		if (pagedListParameters.getLimit() != null) {
			uriBuilder = uriBuilder.queryParam("limit",
					String.valueOf(pagedListParameters.getLimit()));
		}
		if (pagedListParameters.getOffset() != null) {
			uriBuilder = uriBuilder.queryParam("offset",
					String.valueOf(pagedListParameters.getOffset()));
		}
		if (pagedListParameters.getSince() != null) {
			uriBuilder = uriBuilder.queryParam("since",
					String.valueOf(pagedListParameters.getSince()));
		}
		if (pagedListParameters.getUntil() != null) {
			uriBuilder = uriBuilder.queryParam("until",
					String.valueOf(pagedListParameters.getUntil()));
		}
		return uriBuilder;
	}

	@SuppressWarnings("unused")
	private JsonNode fetchConnectionList(String baseUri,
			PagingParameters pagedListParameters, String fields) {
		URIBuilder uriBuilder = URIBuilder.fromUri(baseUri);
		uriBuilder.queryParam("fields", fields);
		uriBuilder = appendPagedListParameters(pagedListParameters, uriBuilder);
		URI uri = uriBuilder.build();
		JsonNode responseNode = facebook.restOperations().getForObject(uri,
				JsonNode.class);
		return responseNode;
	}
}
