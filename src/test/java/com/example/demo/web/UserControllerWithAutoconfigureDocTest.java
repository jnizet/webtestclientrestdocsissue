package com.example.demo.web;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * restdocs test for UserController with AutoConfigureDocTest: doesn't pass
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
public class UserControllerWithAutoconfigureDocTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldGetUser() {
        webTestClient.get()
                     .uri("/users/{id}", 42L)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(document("users/get1"));

        /* This throws the following exception:
        java.lang.NullPointerException
	at java.util.HashMap.putMapEntries(HashMap.java:501)
	at java.util.HashMap.<init>(HashMap.java:490)
	at org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.retrieveConfiguration(WebTestClientRestDocumentation.java:143)
	at org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.lambda$document$0(WebTestClientRestDocumentation.java:80)
	at org.springframework.test.web.reactive.server.DefaultWebTestClient$DefaultBodyContentSpec.lambda$consumeWith$3(DefaultWebTestClient.java:540)
	at org.springframework.test.web.reactive.server.ExchangeResult.assertWithDiagnostics(ExchangeResult.java:197)
	at org.springframework.test.web.reactive.server.DefaultWebTestClient$DefaultBodyContentSpec.consumeWith(DefaultWebTestClient.java:540)
	at com.example.demo.web.UserControllerWithAutoconfigureDocTest.shouldGetUser(UserControllerWithAutoconfigureDocTest.java:31)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:532)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:115)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:171)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:72)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:167)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:114)
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
         */
    }
}
