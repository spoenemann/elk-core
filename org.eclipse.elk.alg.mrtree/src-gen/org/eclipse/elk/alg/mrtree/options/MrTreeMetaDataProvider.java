/**
 * Copyright (c) 2015, 2020 Kiel University and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.elk.alg.mrtree.options;

import java.util.EnumSet;
import org.eclipse.elk.core.data.ILayoutMetaDataProvider;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;

/**
 * Declarations for the ELK Tree layout algorithm.
 */
@SuppressWarnings("all")
public class MrTreeMetaDataProvider implements ILayoutMetaDataProvider {
  /**
   * Default value for {@link #WEIGHTING}.
   */
  private static final OrderWeighting WEIGHTING_DEFAULT = OrderWeighting.DESCENDANTS;
  
  /**
   * Which weighting to use when computing a node order.
   */
  public static final IProperty<OrderWeighting> WEIGHTING = new Property<OrderWeighting>(
            "org.eclipse.elk.mrtree.weighting",
            WEIGHTING_DEFAULT,
            null,
            null);
  
  /**
   * Default value for {@link #SEARCH_ORDER}.
   */
  private static final TreeifyingOrder SEARCH_ORDER_DEFAULT = TreeifyingOrder.DFS;
  
  /**
   * Which search order to use when computing a spanning tree.
   */
  public static final IProperty<TreeifyingOrder> SEARCH_ORDER = new Property<TreeifyingOrder>(
            "org.eclipse.elk.mrtree.searchOrder",
            SEARCH_ORDER_DEFAULT,
            null,
            null);
  
  public void apply(final org.eclipse.elk.core.data.ILayoutMetaDataProvider.Registry registry) {
    registry.register(new LayoutOptionData.Builder()
        .id("org.eclipse.elk.mrtree.weighting")
        .group("")
        .name("Weighting of Nodes")
        .description("Which weighting to use when computing a node order.")
        .defaultValue(WEIGHTING_DEFAULT)
        .type(LayoutOptionData.Type.ENUM)
        .optionClass(OrderWeighting.class)
        .targets(EnumSet.of(LayoutOptionData.Target.PARENTS))
        .visibility(LayoutOptionData.Visibility.VISIBLE)
        .create()
    );
    registry.register(new LayoutOptionData.Builder()
        .id("org.eclipse.elk.mrtree.searchOrder")
        .group("")
        .name("Search Order")
        .description("Which search order to use when computing a spanning tree.")
        .defaultValue(SEARCH_ORDER_DEFAULT)
        .type(LayoutOptionData.Type.ENUM)
        .optionClass(TreeifyingOrder.class)
        .targets(EnumSet.of(LayoutOptionData.Target.PARENTS))
        .visibility(LayoutOptionData.Visibility.VISIBLE)
        .create()
    );
    new org.eclipse.elk.alg.mrtree.options.MrTreeOptions().apply(registry);
  }
}
