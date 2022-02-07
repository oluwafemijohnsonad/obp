package code.api.BahrainOBF.v1_0_0

import code.api.APIFailureNewStyle
import code.api.berlin.group.v1_3.JvalueCaseClass
import net.liftweb.json
import net.liftweb.json._
import code.api.util.APIUtil.{defaultBankId, _}
import code.api.util.NewStyle
import code.api.util.ErrorMessages._
import code.api.util.ApiTag._
import code.api.util.NewStyle.HttpCode
import code.bankconnectors.Connector
import code.model._
import code.util.Helper
import code.views.Views
import net.liftweb.common.Full
import net.liftweb.http.rest.RestHelper
import com.github.dwickern.macros.NameOf.nameOf
import scala.collection.immutable.Nil
import scala.collection.mutable.ArrayBuffer
import com.openbankproject.commons.ExecutionContext.Implicits.global
import scala.concurrent.Future
import code.api.BahrainOBF.v1_0_0.ApiCollector
import code.api.util.ApiTag

object APIMethods_EventNotificationApi extends RestHelper {
    val apiVersion =  ApiCollector.apiVersion
    val resourceDocs = ArrayBuffer[ResourceDoc]()
    val apiRelations = ArrayBuffer[ApiRelation]()
    protected implicit def JvalueToSuper(what: JValue): JvalueCaseClass = JvalueCaseClass(what)

    val endpoints = 
      eventNotificationsPost ::
      Nil

            
     resourceDocs += ResourceDoc(
       eventNotificationsPost, 
       apiVersion, 
       nameOf(eventNotificationsPost),
       "POST", 
       "/event-notifications", 
       "The ASPSP to send an event-notification resource to a TPP",
       s"""${mockedDataText(true)}
            
            """,
       json.parse("""{
  "aud" : "aud",
  "sub" : "http://example.com/aeiou",
  "iss" : "iss",
  "toe" : 0,
  "txn" : "txn",
  "iat" : 0,
  "events" : {
    "urn:bh:org:openbanking:events:resource-update" : {
      "subject" : {
        "http://openbanking.org.bh/rty" : "http://openbanking.org.bh/rty",
        "subject_type" : "subject_type",
        "http://openbanking.org.bh/rlk" : [ {
          "link" : "link",
          "version" : "version"
        }, {
          "link" : "link",
          "version" : "version"
        } ],
        "http://openbanking.org.bh/rid" : "http://openbanking.org.bh/rid"
      }
    },
    "urn:bh:org:openbanking:events:account-access-consent-linked-account-update" : {
      "reason" : "reason",
      "subject" : {
        "http://openbanking.org.bh/rty" : "http://openbanking.org.bh/rty",
        "subject_type" : "subject_type",
        "http://openbanking.org.bh/rlk" : [ {
          "link" : "link",
          "version" : "version"
        }, {
          "link" : "link",
          "version" : "version"
        } ],
        "http://openbanking.org.bh/rid" : "http://openbanking.org.bh/rid"
      }
    },
    "urn:bh:org:openbanking:events:consent-authorization-revoked" : {
      "reason" : "reason",
      "subject" : {
        "http://openbanking.org.bh/rty" : "http://openbanking.org.bh/rty",
        "subject_type" : "subject_type",
        "http://openbanking.org.bh/rlk" : [ {
          "link" : "link",
          "version" : "version"
        }, {
          "link" : "link",
          "version" : "version"
        } ],
        "http://openbanking.org.bh/rid" : "http://openbanking.org.bh/rid"
      }
    }
  },
  "jti" : "jti"
}"""),
       json.parse(""""""),
       List(UserNotLoggedIn, UnknownError),
       ApiTag("Event Notification") :: apiTagMockedData :: Nil
     )

     lazy val eventNotificationsPost : OBPEndpoint = {
       case "event-notifications" :: Nil JsonPost _ => {
         cc =>
           for {
             (Full(u), callContext) <- authenticatedAccess(cc, UserNotLoggedIn)
             } yield {
            (json.parse(""""""), callContext)
           }
         }
       }

}



