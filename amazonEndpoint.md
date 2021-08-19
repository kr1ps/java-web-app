---
---

# Amazon autocomplete API

Amazon autocomplete API is the service used by the marketplace to find the keyword suggestion list related to the prefix inserted in the search field.

The component below is the search field that consumes the API.

![image.png](https://stoplight.io/api/v1/projects/cHJqOjc2Nzc3/images/n9cSPzUDvME)


**API details:**

name | VALUE
---------|----------
**PATH:**  | https://completion.amazon.com/api/2017/suggestions
**METHOD:**  | GET


**PARAMS**


Name     | Description| Possible values
---------|----------  |---------
session-id | Id of the opened session | String type
customer-id | Id of the logged client |String type
request-id | Id of the current request | String type
page-type | Page that triggered the event  | Gateway, Search, Detail,YourAccount, others...
lop | language | en_US, es_US, others...
site-variant | Platform type | desktop, MOBILE
client-info | Name of the client ui | amazon-search-ui
mid | Marketplace Id (by Country) | The possible are here: https://docs.developer.amazonservices.com/en_US/dev_guide/DG_Endpoints.html
alias | Product Category | For general category the value is 'aps' otherwise the current selected category in the page is sent
ks | Last key pressed in the search bar  | Numeric representation of the key event https://css-tricks.com/snippets/javascript/javascript-keycodes
prefix | Keyword to search | Data inserted into search textfield
event | The event that triggers the call  | onKeyPress, onFocusEmptySearchTerm,
limit | Size limit of the suggestion list | The limit of results is 6 for mobile and 11 for desktop client.
b2b | Indicates if is a amazon business account | -
fresh | Indicate if is a fresh customer | -
fb | fallback? | -
suggestion-type | Suggestion list type | a9, a9-xcat, a9-xcat-only, nextSearch, recentSearch, trendSearch, bia, qu, help, KEYWORD, BUY_IT_AGAIN, TRENDING_SEARCHES, WIDGET

**This is an example of a request:**

curl --location --request GET 'https://completion.amazon.com/api/2017/suggestions?session-id=146-6881428-7629617&customer-id=A1GATD9DWP1DZY&request-id=QYR3B837PT0RH754W67E&page-type=Search&lop=en_US&site-variant=desktop&client-info=amazon-search-ui&mid=ATVPDKIKX0DER&alias=aps&ks=80&prefix=app&event=onKeyPress&limit=11&b2b=0&fresh=0&fb=1&suggestion-type=KEYWORD&suggestion-type=WIDGET&_=1627768243743'
















