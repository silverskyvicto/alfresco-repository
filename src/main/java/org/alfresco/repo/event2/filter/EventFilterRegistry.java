/*
 * #%L
 * Alfresco Repository
 * %%
 * Copyright (C) 2005 - 2020 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software.
 * If the software was purchased under a paid Alfresco license, the terms of
 * the paid license agreement will prevail.  Otherwise, the software is
 * provided under the following open source license terms:
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.repo.event2.filter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * Holds {@link EventFilter} implementations.
 *
 * @author Jamal Kaabi-Mofrad
 */
public class EventFilterRegistry implements BeanFactoryAware
{
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        this.beanFactory = beanFactory;
    }

    /**
     * Return the filter bean instance that uniquely matches the given object type.
     *
     * @param filterClass the event filter type that the bean must match
     * @return an instance of the filter bean matching the required type
     * @throws NoSuchBeanDefinitionException - if no bean of the given type was found
     */
    public <F extends EventFilter<?>> F getFilter(Class<F> filterClass)
    {
        return beanFactory.getBean(filterClass);
    }

    public NodeTypeFilter getNodeTypeFilter()
    {
        return getFilter(NodeTypeFilter.class);
    }

    public NodeAspectFilter getNodeAspectFilter()
    {
        return getFilter(NodeAspectFilter.class);
    }

    public NodePropertyFilter getNodePropertyFilter()
    {
        return getFilter(NodePropertyFilter.class);
    }

    public EventUserFilter getEventUserFilter()
    {
        return getFilter(EventUserFilter.class);
    }
}