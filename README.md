# AppliftingSpaceXApp
Android mobile app for visualization of SpaceX API.
It shows countdown to next launch, list of filterable past and upcoming launches, current SpaceX rockets and company info.

Libraries stack
UI: Jetpack Compose
DI: Hilt
Network: Retrofit + okHttp + Gson
Local user storage: DataStore API
Images: Coil
Pagination: Paging 3

Caching:
All HTTP requests are cached with network interceptor in Retrofit.
All downloaded images are implicitly cached with Coil library.
Paginated responses in Launches are cached in ViewModel context by Paging 3 library

Launches filtering:
By date_local (from->to)
By success
By rocket_id
Sort asc/desc

Tests:
1 simple instrumentation UI test
1 simple unit test
