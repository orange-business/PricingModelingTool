package com.orange.ru.swf;

import com.orange.ru.core.userdetails.OrangeUser;
import com.orange.ru.core.userdetails.OrangeUserBuilder;
import com.orange.ru.service.AdminService;
import org.easymock.EasyMock;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

/**
 *
 * User: Зайнуллин Радик
 * Date: 02.08.13
 */
public class AuthorizationFlowExecutionTests extends AbstractXmlFlowExecutionTests {
  private AdminService adminService;
  protected void setUp() { adminService = EasyMock.createMock(AdminService.class); }

  @Override
  protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
    return resourceFactory.createFileResource("src/main/webapp/WEB-INF/private/admin/create_user/create_user-flow.xml");
  }
  @Override
  protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
    builderContext.registerBean("adminService", adminService);
  }
  public void testStartAuthorizationFlow() {
    OrangeUser user = createTestUser();
    EasyMock.expect(adminService.persistOrangeUser(user)).andReturn(user);
    EasyMock.replay(adminService);
    MutableAttributeMap input = new LocalAttributeMap();
    input.put("userId", "1");
    MockExternalContext context = new MockExternalContext();
    context.setCurrentUser("keith");
    startFlow(input, context);

    assertCurrentStateEquals("enterBookingDetails");
    assertResponseWrittenEquals("enterBookingDetails", context);
    assertTrue(getRequiredFlowAttribute("booking") instanceof OrangeUser);

    EasyMock.verify(adminService);
  }
  private OrangeUser createTestUser() {
    OrangeUser user = new OrangeUserBuilder().firstName("Ильдар").lastName("Абузяров")
        .email("iliay@yandex.ru").password("hello").assembleProduct();
    return user;
  }
}
