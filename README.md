# Android_MVP_Sample

### MVP Pattern
- - -

![MVP1](https://user-images.githubusercontent.com/30828236/56715159-d5f3c980-6771-11e9-9962-36916bbe9924.png)

 - #### Model

 내부적으로 쓰이는 데이터를 저장하고, 처리하는 역할을 한다. 흔히 'Business Logic' 이라고 부르는 부분이다. View, Presenter 등 다른 어떤 요소에도 의존적이지 않은 독립적인 영역이다.
 
  ~~~java

public class ResultUrl {
    private String message;
    private String code;
    private ResultFormat result;

    public ResultUrl(String message, String code, ResultFormat result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }
 }
   
 ~~~
  
 ~~~java

public class GetServerResponseImpl implements MainContractor.GetServerResponse {

    @Override
    public void getNoticeURL(final OnFinishedListener onFinishedListener, String url) {

        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);

        retrofit.sendShortURL(url).enqueue(new Callback<ResultUrl>() {
            @Override
            public void onResponse(Call<ResultUrl> call, Response<ResultUrl> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        onFinishedListener.onFinished(response.body().getResult().getUrl());
                        Log.e("result url",""+response.body().getResult().getUrl());
                    }
                }
            }
            @Override
            public void onFailure(Call<ResultUrl> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e("shotURL Failure",""+t.toString());
            }
        });
    }
}
   
 ~~~
 


 - #### View

 실제 View 에 대한 직접적인 접근을 담당한다. 안드로이드에서 Activity/Fragment 는 View의 일부로 정의한다. View의 일부로 정의한다. View 에서 발생하는 이벤트는 직접 핸들링 할 수 있으나 Presenter 에 위임하도록 한다. 위임하는 방법은 Activity 가 View Interface 를 구현해서 Presenter 에서 코드를 만들 인터페이스를 갖도록 하면 된다. 이를 통해 특정 뷰와 결합되지 않고 가상 뷰를 구현해서 간단한 유닛 테스트를 실행할 수 있다.
 
   ~~~java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this, new GetServerResponseImpl());
        mainPresenter.attachView(this);
    }
   
 ~~~

 - #### Presenter

 본질적으로는 MVC의 컨트롤러와 같지만, 뷰에 연결되는 것이 아니라 Interface로 연결된다는 점이 다르다. 이에 따라 MVC가 가진 테스트 가능성 문제와 함께 모듈화/유연성 문제 역시 해결한다. Presenter의 역할을 정의한다면 View 와 Model 사이에서 자료 전달 역할을 합니다.
 
  ~~~java
 
public interface MainContractor {
    interface View {
        void showResult();

        void setResultURL(String url);

    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void loadURL(Context context, String url);
    }

    interface GetServerResponse {

        interface OnFinishedListener {
            void onFinished(String resultURL);
            void onFailure(Throwable t);
        }

        void getNoticeURL(OnFinishedListener onFinishedListener, String url);
    }
}
   
 ~~~
 
  ~~~java
 
public class MainPresenter implements MainContractor.Presenter, MainContractor.GetServerResponse.OnFinishedListener{

    private MainContractor.View view;
    private MainContractor.GetServerResponse getNoticeIntractor;

    public MainPresenter(MainContractor.View view, MainContractor.GetServerResponse getNoticeIntractor) {
        this.view = view;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void attachView(MainContractor.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {view = null;}

    @Override
    public void loadURL(Context context, String url) {
        getNoticeIntractor.getNoticeURL(this,url);
    }

    @Override
    public void onFinished(String resultURL) {
        view.setResultURL(resultURL);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("network onFailure",""+t.toString());
    }
}
   
 ~~~
 
 
- - -

### RxAndroid 기본
- - - 

 - **Observable** : 비즈니스 로직을 이용해 데이터를 발행함
 - **구독자** : Observable에서 발행한 데이터를 구독함
 - **스케줄러** : 스케줄러를 통해서 Observable, 구독자가 어느 스레드에서 실행될지 결정할 수 있다.

- - -
~~~java
//1.Observable 생성
Observable.create()
	//2.구독자 이용
	.subscribe();
		
	//3.스케줄러 이용
	.subscribeOn(Schedulers.io())
	.observeOn(AndroidSchedulers.mainThread())
~~~

| 스케줄러 이름 | 설명 
|---|:---:|
| `AndroidSchedulers.mainThread()` | 안드로이드의 UI 스레드에서 동작하는 스케줄러이다. |
| `HandlerScheduler.from(handler)` | 특정 핸들러에 의존하여 동작하는 스케줄러이다. |


### RxLifeCycle 컴포넌트
- - -

| 컴포넌트 | 설명 
|---|:---:|
| `RxActivity` | 액티비티에 대응한다.|
| `RxDialogFragment` | Native/Support 라이브러리인 DialogFragment에 대응한다. |
| `RxFragment` | Native/Support 라이브러리인 Fragment에 대응 |
| `RxPreferenceFragment` | PreferenceFragment에 대응한다. |
| `RxAppCompatActivity` | Support 라이브러리 AppCompatActivity에 대응한다. |
| `RxAppCompatDialogFragment` | Support 라이브러리 AppCompatDialogFragment에 대응한다. |
| `RxFragmentActivity` | Support 라이브러리 FragmentActivity에 대응한다. |

- - - 
#### RxLifeCycle 라이브러리 사용
`implementation 'com.trello.rxlifecycle3:rxlifecycle:3.0.0'`

// If you want to bind to Android-specific lifecycles

`implementation 'com.trello.rxlifecycle3:rxlifecycle-android:3.0.0'`

// If you want pre-written Activities and Fragments you can subclass as providers

`implementation 'com.trello.rxlifecycle3:rxlifecycle-components:3.0.0'`

- - -

### RxAndroid 와 AsyncTask클래스의 기능 비교
- - -

| AsyncTask | RxAndroid |
|---|:---:|
| `excute()` | subscribe()|
| `doInBackground()` | 리액티브 연산자와 함께 사용하는 onSubscibe() |
| `onPostExecuted()` | observer |

#### 안드로이드 UI 스레드 통신구조

<img src ="https://user-images.githubusercontent.com/30828236/57628090-37ba8d00-75d4-11e9-97da-36f1c9a699c2.png"/>

- - -

#### AsyncTask 동작 원리

<img width=600 height=700 src = "https://user-images.githubusercontent.com/30828236/57628258-87995400-75d4-11e9-98d2-44847c140d03.png"/>
