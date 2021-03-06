<h1>SpringLock</h1>
<b>让你用更优雅的方式加锁，基于spring的注解的加锁实现方式</b>
<h2>功能简介</h2>
这个jar包能够让你使用更优雅的方式加锁——<b>基于注解的加锁方式</b>。
一直以来，线程安全问题是考验web后端人员的主要问题之一，加锁是解决线程安全问题的主要形式。
从锁的形式上看，锁可以分为乐观锁和悲观锁。从锁的内容上看，锁可以分为互斥锁和共享锁。
这个Jar包旨在提供全套的锁解决方案，让你使用一个注解就能对目标进行加锁，就像下面这样子。<br />
<code>@Synchronized</code><br />
<code>public Consumer queryConsumerById(String id){...}</code><br />
等同于<br />
<code>public synchronized Consumer queryConsumerById(String id){...}</code><br />
再比如<br />
<code>@ReadLock</code><br />
<code>public Consumer queryConsumerById(String id){...}</code><br />
等同于<br />
<code>public Consumer queryConsumerById(String id){</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;this.readLock.lock();</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;try{</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;}finally{</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;this.readLock.unlock();</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;}</code><br />
<code>&nbsp;&nbsp;&nbsp;&nbsp;return ...</code><br />
<code>}</code><br />
如何？看了上面的简介是不是觉得使用很方便呢？
<h2>版本更新</h2>
<table>
<tr>
<th>版本号</th><th>更新内容</th><th>更新日期</th>
</tr>
<tr>
<td>0.1.0.0</td><td>完成基本框架搭建；新增Synchronized注解，实现同步代码块</td><td>2022年1月27日</td>
</tr>
<tr>
<td>0.2.0.0</td><td>开发读锁注解，目前能通过注解获取内部已有的读锁</td><td>2022年1月28日</td>
</tr>
<tr>
<td>0.2.1.0</td><td>开发读锁注解，目前已实现注解编译期间添加读写锁</td><td>2022年1月28日</td>
</tr>
<tr>
<td>0.2.2.0</td><td>完成读锁开发，目前可基于注解动态生成读写锁和读锁</td><td>2022年1月28日</td>
</tr>
<tr>
<td>0.2.2.1</td><td>修复读锁逻辑BUG</td><td>2022年1月28日</td>
</tr>
<tr>
<td>0.3.0.0</td><td>完成读写锁开发，通过测试确定可用</td><td>2022年1月29日</td>
</tr>
<tr>
<td>0.3.0.1</td><td>修改maven打包方式</td><td>2022年1月29日</td>
</tr>
<tr>
<td>0.3.1.0</td><td>改进互斥锁，现在可以使用自定义的变量当作锁</td><td>2022年1月30日</td>
</tr>
<tr>
<td>0.3.2.0</td><td>修复读写锁没导入包的BUG；开发乐观锁注解</td><td>2022年1月30日</td>
</tr>
<tr>
<td>0.3.3.0</td><td>完成乐观锁处理器开发，目前可以将乐观锁动态编译进入类成员</td><td>2022年1月30日</td>
</tr>
<tr>
<td>0.4.0.0</td><td>完成乐观锁开发。目前有互斥锁、读写锁、乐观锁，均已测试可用</td><td>2022年1月30日</td>
</tr>
<tr>
<td>0.5.0.0</td><td>优化执行逻辑，提升互斥锁性能</td><td>2021年1月31日</td>
</tr>
<tr>
<td>0.6.0.0</td><td>完善读写锁，现在可以自定义公平非公平</td><td>2022年2月2日</td>
</tr>
<tr>
<td>0.7.0.0</td><td>完善读写锁，现在fair自定义属性可以只写在一处</td><td>2022年2月3日</td>
</tr>
<tr>
<td>0.8.0.0</td><td>完善读锁，提供最长等待时间，最长执行时间，是否强制执行等属性，全方位避免死锁</td><td>2022年2月4日</td>
</tr>
<tr>
<td>0.8.1.0</td><td>完善写锁，提供最长等待时间，最长执行时间，是否强制执行等属性，全方位避免死锁</td><td>2022年2月4日</td>
</tr>
<tr>
<td>0.9.0.0</td><td>完善读写锁注解，现在可以使用自定义的读写锁</td><td>2022年2月4日</td>
</tr>
<tr>
<td>1.0.0.0</td><td>修复时长溢出的BUG;完善乐观锁，现在可以使用自定义的乐观锁，并指定最长等待时间、最长执行时间、是否强制执行等，全方位避免死锁</td><td>2022年2月5日</td>
</tr>
<tr>
<td>1.1.0.0</td><td>新增注解<code>@MakeReadWriteLocks</code>用于对类生成自定义读写锁，其<code>fair</code>属性来自于注解的类下<code>@ReadLock</code>注解或<code>@WriteLock</code>注解的<code>fair</code>属性值</td><td>2022年2月6日</td>
</tr>
<tr>
<td>1.2.0.0</td><td>废弃<code>@MakeReadWriteLocks</code>注解，现在不需要该注解也能正常识别自定义的锁；精简代码，提高编译速度</td><td>2022年2月6日</td>
</tr>
<tr>
<td>1.2.1.0</td><td>锁注解允许继承</td><td>2022年2月19日</td>
</tr>
</table>
