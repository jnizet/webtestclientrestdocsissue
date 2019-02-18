package com.example.demo.web;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;

/**
 * restdocs test for UserController with configuration done as documented in the Spring REST Docs documentation: doesn't pass
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class UserControllerWithWebApplicationContextDocTest {

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToApplicationContext(webApplicationContext)
                                          .configureClient()
                                          .filter(documentationConfiguration(restDocumentation))
                                          .build();
    }

    @Test
    void shouldGetUser() {
        webTestClient.get()
                     .uri("/users/{id}", 42L)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(document("users/get2"));

        /* This throws the following exception:
        org.junit.jupiter.api.extension.ParameterResolutionException: Failed to resolve parameter [org.springframework.web.context.WebApplicationContext arg0] in method [public void com.example.demo.web.UserControllerWithWebApplicationContextDocTest.setUp(org.springframework.web.context.WebApplicationContext,org.springframework.restdocs.RestDocumentationContextProvider)]

	at org.junit.jupiter.engine.execution.ExecutableInvoker.resolveParameter(ExecutableInvoker.java:221)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.resolveParameters(ExecutableInvoker.java:174)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.resolveParameters(ExecutableInvoker.java:135)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:116)
	at org.junit.jupiter.engine.descriptor.ClassTestDescriptor.invokeMethodInExtensionContext(ClassTestDescriptor.java:436)
	at org.junit.jupiter.engine.descriptor.ClassTestDescriptor.lambda$synthesizeBeforeEachMethodAdapter$14(ClassTestDescriptor.java:424)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeBeforeEachMethods$2(TestMethodTestDescriptor.java:136)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:72)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeBeforeMethodsOrCallbacksUntilExceptionOccurs(TestMethodTestDescriptor.java:156)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeBeforeEachMethods(TestMethodTestDescriptor.java:135)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:110)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:59)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$4(NodeTestTask.java:108)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:72)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:98)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:74)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$4(NodeTestTask.java:112)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:72)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:98)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:74)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$4(NodeTestTask.java:112)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:72)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:98)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:74)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:220)
	at org.junit.platform.launcher.core.DefaultLauncher.lambda$execute$6(DefaultLauncher.java:188)
	at org.junit.platform.launcher.core.DefaultLauncher.withInterceptedStreams(DefaultLauncher.java:202)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:181)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:128)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:74)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.web.context.WebApplicationContext' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.raiseNoMatchingBeanFound(DefaultListableBeanFactory.java:1654)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1213)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1167)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.resolveDependency(AbstractAutowireCapableBeanFactory.java:462)
	at org.springframework.test.context.junit.jupiter.ParameterAutowireUtils.resolveDependency(ParameterAutowireUtils.java:125)
	at org.springframework.test.context.junit.jupiter.SpringExtension.resolveParameter(SpringExtension.java:179)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.resolveParameter(ExecutableInvoker.java:207)
	... 39 more
         */
    }
}
