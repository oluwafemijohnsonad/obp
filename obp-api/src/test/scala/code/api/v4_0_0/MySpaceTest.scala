package code.api.v4_0_0

import com.openbankproject.commons.model.ErrorMessage
import code.api.util.APIUtil.OAuth._
import code.api.util.ApiRole
import com.openbankproject.commons.util.ApiVersion
import code.api.util.ErrorMessages.UserNotLoggedIn
import code.api.v4_0_0.OBPAPI4_0_0.Implementations4_0_0
import code.entitlement.Entitlement
import com.github.dwickern.macros.NameOf.nameOf
import net.liftweb.common.Box
import org.scalatest.Tag

class MySpaceTest extends V400ServerSetup {
  /**
    * Test tags
    * Example: To run tests with tag "getPermissions":
    * 	mvn test -D tagsToInclude
    *
    *  This is made possible by the scalatest maven plugin
    */
  object VersionOfApi extends Tag(ApiVersion.v4_0_0.toString)
  object ApiEndpoint1 extends Tag(nameOf(Implementations4_0_0.getMySpaces))

  
  feature(s"test $ApiEndpoint1 version $VersionOfApi") {
    scenario("We will call the endpoint without user credentials", ApiEndpoint1, VersionOfApi) {
      When("We make a request v4.0.0")
      val request400 = (v4_0_0_Request / "my" / "spaces").GET
      val response400 = makeGetRequest(request400)
      Then("We should get a 401")
      response400.code should equal(401)
      response400.body.extract[ErrorMessage].message should equal(UserNotLoggedIn)
    }
    scenario("We will call the endpoint return empty List", ApiEndpoint1, VersionOfApi) {
      When("We make a request v4.0.0")
      val request400 = (v4_0_0_Request / "my" / "spaces").GET <@ (user1)
      val response400 = makeGetRequest(request400)
      Then("We should get a 200")
      response400.code should equal(200)
      response400.body.extract[MySpaces].bank_ids.length should be (0)
    }
    scenario("We will call the endpoint return proper List", ApiEndpoint1, VersionOfApi) {
      When("We make a request v4.0.0")
      Entitlement.entitlement.vend.addEntitlement(testBankId1.value, resourceUser1.userId, ApiRole.CanReadDynamicResourceDocsAtOneBank.toString)
      val request400 = (v4_0_0_Request / "my" / "spaces").GET <@ (user1)
      val response400 = makeGetRequest(request400)
      Then("We should get a 200")
      response400.code should equal(200)
      response400.body.extract[MySpaces].bank_ids.length should be (1)
    }
  }

}
