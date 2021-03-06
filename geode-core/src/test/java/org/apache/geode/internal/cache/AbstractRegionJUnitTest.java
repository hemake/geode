/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.geode.internal.cache;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import org.apache.geode.CancelCriterion;
import org.apache.geode.cache.AttributesMutator;
import org.apache.geode.cache.CacheLoaderException;
import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.EntryExistsException;
import org.apache.geode.cache.EntryNotFoundException;
import org.apache.geode.cache.InterestResultPolicy;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionAttributes;
import org.apache.geode.cache.RegionExistsException;
import org.apache.geode.cache.TimeoutException;
import org.apache.geode.cache.client.internal.ServerRegionProxy;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.distributed.internal.membership.InternalDistributedMember;
import org.apache.geode.internal.Version;
import org.apache.geode.internal.cache.extension.ExtensionPoint;
import org.apache.geode.internal.cache.extension.SimpleExtensionPoint;
import org.apache.geode.internal.cache.versions.RegionVersionVector;
import org.apache.geode.internal.cache.versions.VersionSource;
import org.apache.geode.internal.cache.versions.VersionTag;
import org.apache.geode.test.junit.categories.UnitTest;

/**
 * Unit tests for {@link AbstractRegion}.
 *
 * @since GemFire 8.1
 */
@Category(UnitTest.class)
public class AbstractRegionJUnitTest {

  /**
   * Test method for {@link AbstractRegion#getExtensionPoint()}.
   *
   * Assert that method returns a {@link SimpleExtensionPoint} instance and assume that
   * {@link org.apache.geode.internal.cache.extension.SimpleExtensionPointJUnitTest} has covered the
   * rest.
   */
  @Test
  public void testGetExtensionPoint() {
    // final Cache cache = new MockCache();
    final AbstractRegion region = new MockRegion(null, 0, false, 0, 0);
    final ExtensionPoint<Region<?, ?>> extensionPoint = region.getExtensionPoint();
    assertNotNull(extensionPoint);
    assertEquals(extensionPoint.getClass(), SimpleExtensionPoint.class);
  }

  @SuppressWarnings("rawtypes")
  private static class MockRegion extends AbstractRegion {

    /**
     * @see AbstractRegion#AbstractRegion(InternalCache, int, boolean, long, long)
     */
    @SuppressWarnings("deprecation")
    private MockRegion(GemFireCacheImpl cache, int serialNumber, boolean isPdxTypeRegion,
        long lastAccessedTime, long lastModifiedTime) {
      super(cache, serialNumber, isPdxTypeRegion, lastAccessedTime, lastModifiedTime);
    }

    @Override
    public String getName() {
      throw new UnsupportedOperationException();
    }

    @Override
    public String getFullPath() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Region getParentRegion() {
      throw new UnsupportedOperationException();
    }

    @Override
    public RegionAttributes getAttributes() {
      throw new UnsupportedOperationException();
    }

    @Override
    public AttributesMutator getAttributesMutator() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void invalidateRegion(Object aCallbackArgument) throws TimeoutException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void localInvalidateRegion(Object aCallbackArgument) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void destroyRegion(Object aCallbackArgument)
        throws CacheWriterException, TimeoutException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void localDestroyRegion(Object aCallbackArgument) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void saveSnapshot(OutputStream outputStream) throws IOException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void loadSnapshot(InputStream inputStream)
        throws IOException, ClassNotFoundException, CacheWriterException, TimeoutException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Region getSubregion(String path) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Region createSubregion(String subregionName, RegionAttributes aRegionAttributes)
        throws RegionExistsException, TimeoutException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set subregions(boolean recursive) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Entry getEntry(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object put(Object key, Object value, Object aCallbackArgument)
        throws TimeoutException, CacheWriterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void create(Object key, Object value, Object aCallbackArgument)
        throws TimeoutException, EntryExistsException, CacheWriterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void invalidate(Object key, Object aCallbackArgument)
        throws TimeoutException, EntryNotFoundException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void localInvalidate(Object key, Object aCallbackArgument)
        throws EntryNotFoundException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object destroy(Object key, Object aCallbackArgument)
        throws TimeoutException, EntryNotFoundException, CacheWriterException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void localDestroy(Object key, Object aCallbackArgument) throws EntryNotFoundException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set keySet() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Collection values() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set entrySet(boolean recursive) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object getUserAttribute() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setUserAttribute(Object value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDestroyed() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValueForKey(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Lock getRegionDistributedLock() throws IllegalStateException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Lock getDistributedLock(Object key) throws IllegalStateException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void writeToDisk() {
      throw new UnsupportedOperationException();
    }

    @Override
    public SelectResults query(String queryPredicate) throws FunctionDomainException,
        TypeMismatchException, NameResolutionException, QueryInvocationTargetException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void forceRolling() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set entrySet() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map map) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map map, Object aCallbackArgument) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void removeAll(Collection keys) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void removeAll(Collection keys, Object aCallbackArgument) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void registerInterest(Object key) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterest(Object key, InterestResultPolicy policy) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterestRegex(String regex) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterestRegex(String regex, InterestResultPolicy policy) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void unregisterInterest(Object key) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void unregisterInterestRegex(String regex) {
      throw new UnsupportedOperationException();

    }

    @Override
    public List getInterestList() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void registerInterest(Object key, boolean isDurable) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterest(Object key, boolean isDurable, boolean receiveValues) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterest(Object key, InterestResultPolicy policy, boolean isDurable,
        boolean receiveValues) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterest(Object key, InterestResultPolicy policy, boolean isDurable) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterestRegex(String regex, boolean isDurable) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterestRegex(String regex, boolean isDurable, boolean receiveValues) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterestRegex(String regex, InterestResultPolicy policy,
        boolean isDurable) {
      throw new UnsupportedOperationException();

    }

    @Override
    public void registerInterestRegex(String regex, InterestResultPolicy policy, boolean isDurable,
        boolean receiveValues) {
      throw new UnsupportedOperationException();

    }

    @Override
    public List getInterestListRegex() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Set keySetOnServer() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKeyOnServer(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int sizeOnServer() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmptyOnServer() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object key, Object value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object replace(Object key, Object value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int[] getDiskDirSizes() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Version[] getSerializationVersions() {
      throw new UnsupportedOperationException();
    }

    @Override
    public CachePerfStats getCachePerfStats() {
      throw new UnsupportedOperationException();
    }

    @Override
    public RegionEntry getRegionEntry(final Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public RegionVersionVector getVersionVector() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object getValueInVM(Object key) throws EntryNotFoundException {
      throw new UnsupportedOperationException();
    }

    @Override
    public Object getValueOnDisk(Object key) throws EntryNotFoundException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void dispatchListenerEvent(EnumListenerEvent op, InternalCacheEvent event) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isUsedForPartitionedRegionAdmin() {
      throw new UnsupportedOperationException();
    }

    @Override
    public ImageState getImageState() {
      throw new UnsupportedOperationException();
    }

    @Override
    public VersionSource getVersionMember() {
      throw new UnsupportedOperationException();
    }

    @Override
    public long updateStatsForPut(RegionEntry entry, long lastModified, boolean lruRecentUse) {
      throw new UnsupportedOperationException();
    }

    @Override
    public FilterProfile getFilterProfile() {
      throw new UnsupportedOperationException();
    }

    @Override
    public ServerRegionProxy getServerProxy() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void unscheduleTombstone(RegionEntry entry) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void scheduleTombstone(RegionEntry entry, VersionTag destroyedVersion) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void addExpiryTaskIfAbsent(RegionEntry entry) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void generateAndSetVersionTag(InternalCacheEvent event, RegionEntry entry) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean cacheWriteBeforeDestroy(EntryEventImpl event, Object expectedOldValue)
        throws CacheWriterException, EntryNotFoundException, TimeoutException {
      throw new UnsupportedOperationException();
    }

    @Override
    public void recordEvent(InternalCacheEvent event) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isConcurrencyChecksEnabled() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean isThisRegionBeingClosedOrDestroyed() {
      throw new UnsupportedOperationException();
    }

    @Override
    public DiskRegion getDiskRegion() {
      throw new UnsupportedOperationException();
    }

    @Override
    public CancelCriterion getCancelCriterion() {
      throw new UnsupportedOperationException();
    }

    @Override
    public int updateSizeOnEvict(Object key, int oldSize) {
      throw new UnsupportedOperationException();
    }

    @Override
    Object get(Object key, Object aCallbackArgument, boolean generateCallbacks,
        EntryEventImpl clientEvent) throws TimeoutException, CacheLoaderException {
      throw new UnsupportedOperationException();
    }

    @Override
    void basicClear(RegionEventImpl regionEvent) {
      throw new UnsupportedOperationException();
    }

    @Override
    boolean generateEventID() {
      throw new UnsupportedOperationException();
    }

    @Override
    protected InternalDistributedMember getMyId() {
      throw new UnsupportedOperationException();
    }

    @Override
    void basicLocalClear(RegionEventImpl rEvent) {
      throw new UnsupportedOperationException();
    }

    @Override
    Map basicGetAll(Collection keys, Object callback) {
      throw new UnsupportedOperationException();
    }

    @Override
    public RegionEntry basicGetEntry(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void invokePutCallbacks(EnumListenerEvent eventType, EntryEventImpl event,
        boolean callDispatchListenerEvent, boolean notifyGateways) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void invokeDestroyCallbacks(EnumListenerEvent eventType, EntryEventImpl event,
        boolean callDispatchListenerEvent, boolean notifyGateways) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void invokeInvalidateCallbacks(EnumListenerEvent eventType, EntryEventImpl event,
        boolean callDispatchListenerEvent) {
      throw new UnsupportedOperationException();
    }

    @Override
    public long getEvictions() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Region createSubregion(String subregionName, RegionAttributes attrs,
        InternalRegionArguments internalRegionArgs)
        throws RegionExistsException, TimeoutException, IOException, ClassNotFoundException {
      throw new UnsupportedOperationException();
    }

    @Override
    protected boolean isCurrentlyLockGrantor() {
      throw new UnsupportedOperationException();
    }

    @Override
    public File[] getDiskDirs() {
      throw new UnsupportedOperationException();
    }

    @Override
    void checkReadiness() {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean supportsConcurrencyChecks() {
      throw new UnsupportedOperationException();
    }

    @Override
    public void addCacheServiceProfile(CacheServiceProfile profile) {
      throw new UnsupportedOperationException();
    }
  }
}
