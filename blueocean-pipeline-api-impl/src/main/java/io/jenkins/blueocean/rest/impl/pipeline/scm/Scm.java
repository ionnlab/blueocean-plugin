package io.jenkins.blueocean.rest.impl.pipeline.scm;

import io.jenkins.blueocean.rest.model.Resource;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.WebMethod;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.json.JsonBody;
import org.kohsuke.stapler.verb.PUT;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Scm Resource
 *
 * @author Vivek Pandey
 */
public abstract class Scm extends Resource {
    public static final String ID="id";
    public static final String URI="uri";
    public static final String CREDENTIAL_ID = "credentialId";
    public static final String VALIDATE = "validate";

    /** SCM id. For example, github, bitbucket etc. */
    @Exported(name = ID)
    public abstract @Nonnull String getId();

    /** SCM URI */
    @Exported(name = URI)
    public abstract @Nonnull String getUri();

    @Exported(name = CREDENTIAL_ID)
    public abstract String getCredentialId();

    /**
     * Validate given accessToken for authentication and authorization.
     *
     * Validation of scopes attached to accessToken is scm dependent but as a general guideline
     * it should check for basic user info (username, email), org access (if applicable) and repo
     * access (read/write)
     *
     * If accessToken is found valid then it should search for existence of credential in user context
     * with id Scm.getId(). If found, it should update the accessToken, if not found then it should create a new
     * credential. In either case it should return the credential Id.
     *
     * @param request access token of an SCM
     *
     * @return credential id. If accessToken is not applicable to this SCM, null is returned.
     */
    @PUT
    @WebMethod(name = VALIDATE)
    public abstract @CheckForNull HttpResponse validateAndCreate(@JsonBody JSONObject request);
}
