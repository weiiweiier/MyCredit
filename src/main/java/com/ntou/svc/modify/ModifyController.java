package com.ntou.svc.modify;


import com.ntou.tool.Common;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Log4j2
@Path(Common.SLASH)
public class ModifyController {

    final String API_SID = "Modify";

    @POST
    @Path(Common.SLASH + API_SID)
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public ModifyRes doController(ModifyReq req) {
        return new Modify().doAPI(req);
    }
}