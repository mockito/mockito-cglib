/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.creation.cglib;

import org.junit.Test;
import org.mockito.cglib.proxy.MethodProxy;
import org.mockito.internal.util.reflection.Whitebox;

import static org.junit.Assert.*;

public class SerializableMockitoMethodProxyTest {

    @Test
    public void shouldCreateCorrectCreationInfo() throws Exception {
        // given
        MethodProxy proxy = MethodProxy.create(String.class, Integer.class, "", "", "");
        SerializableMockitoMethodProxy serializableMockitoMethodProxy = new SerializableMockitoMethodProxy(proxy);
        serializableMockitoMethodProxy.methodProxy = null;

        // when
        MethodProxy methodProxy = serializableMockitoMethodProxy.getMethodProxy();

        // then
        Object info = Whitebox.getInternalState(methodProxy, "createInfo");
        assertEquals(String.class, Whitebox.getInternalState(info, "c1"));
        assertEquals(Integer.class, Whitebox.getInternalState(info, "c2"));
    }

    @Test
    public void shouldCreateCorrectSignatures() throws Exception {
        // given
        MethodProxy proxy = MethodProxy.create(String.class, Integer.class, "a", "b", "c");
        SerializableMockitoMethodProxy serializableMockitoMethodProxy = new SerializableMockitoMethodProxy(proxy);
        serializableMockitoMethodProxy.methodProxy = null;

        // when
        MethodProxy methodProxy = serializableMockitoMethodProxy.getMethodProxy();

        // then
        assertEquals("a", methodProxy.getSignature().getDescriptor());
        assertEquals("b", methodProxy.getSignature().getName());
        assertEquals("c", methodProxy.getSuperName());
    }
}
