XPullToRefreshListView
======================

A library includes three kind of useful ListView 

Library Includes
=======
* 1.PullToRefreshListView  下拉刷新
* 2.LoadMoreListView       到底部自动加载更多
* 3.PullAndLoadListView   下拉刷新+到底部自动加载更多

Feature of PullAndLoadListView  
===
* add function `setCanRefresh(boolean canRefresh, boolean ableToShowHeadView)`  
  to control if set whether it's able to show the headView when it can't refresh  
   NOTE THAT: you _have to_ called this method _before_ `onRefreshComplete()`   
  (add in 2014.1.24)
* add function `setCanRefresh(boolean canRefresh, String text)` and `setCanRefresh(boolean canRefrsh)`  
  It's familiar to  `setCanLoadMore()` .But in here,it's his responsiblity to control whether data can be refresh for more. 
  if you know that it hava no more data to refresh, called `setCanRefresh()` _to tell user about that!_  
  NOTE THAT: you _have to_ called this method _after_ `onRefreshComplete()`   
  (added in 2014.1.19) 
* add function `toRefresh()` in `PullToRefreshListView` to manual refresh!     
  (added in 2014.1.19) 
* add funcion `setCanLoadMore()` in `PullAndLoadListView` to help you control whetherthe data can load more and update the footView  or not  
  (added in 2014.1.15)  




Install
===
1.
```shell
git clone https://github.com/Jayin/XPullToRefreshListView.git
```

2.add this library:
your own project properties -> Android —> Library "add" this project
 


Quick start(PullAndLoadListView)
===
###  Layout
```xml
      <com.coustom.widget.PullAndLoadListView
        android:id="@+id/xlistview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
    </com.coustom.widget.PullAndLoadListVieww>
```

### Activity
```java
   private PullAndLoadListView lv;
   ....
   lv =(PullAndLoadListView)findViewById(R.id.xlistview);
   ....
   lv.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
			    //start a task here
			    lv.onRefreshComplete(); //you must call this method when you finish your task
			    //lv.setCanRefresh(false); ////you must call this method after lv.onRefreshComplete() if you really want to set it's not able to refresh!
			}
		});
    ......
	lv.setOnLoadMoreListener(new OnLoadMoreListener() {

			public void onLoadMore() {
                  //start a task here
              new Task().excute();
			 //	lv.onLoadMoreComplete(); //you must call this method when you finish your task
			}
		});
   
```

```java
class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			//do the long-time work here
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
		   // lv.canLoadMore(false);//if you found that you can't load more data(Like:page is in the end),called this method and listview won't loadmore again!
		    lv.onLoadMoreComplete()
		}

	}

```

Advanced
===
coutom layout by yourself but pay attention to the [IDs]

License
===
The MIT License (MIT)



