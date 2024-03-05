package com.ntou.svc.query;


import com.ntou.tool.Common;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Log4j2
@Path(Common.SLASH)
public class QueryController {

//    @Context
//    private HttpServletRequest httpReq;
    final String API_SID = "Query";

    @POST
    @Path(Common.SLASH + API_SID)
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public QueryRes doController(QueryReq req) throws Exception {
//        return new Query(httpReq).doAPI(req);
        return new Query().doAPI(req);
    }
}