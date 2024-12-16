# Lista Darmowych API GraphQL do Celów Demonstracyjnych

## 1. SpaceX GraphQL API
- **Opis:** API oparte na danych SpaceX, które udostępnia informacje o rakietach, misjach, lądowaniach itp.
- **URL endpointu:**
  ```
  https://spacex.land/graphql/
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    launchesPast(limit: 5) {
      mission_name
      launch_date_utc
      rocket {
        rocket_name
      }
    }
  }
  ```

---

## 2. Countries GraphQL API
- **Opis:** Udostępnia dane o krajach, ich stolicach, językach i innych szczegółach geograficznych.
- **URL endpointu:**
  ```
  https://countries.trevorblades.com/
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    countries {
      name
      capital
      currency
      languages {
        name
      }
    }
  }
  ```

---

## 3. Rick and Morty GraphQL API
- **Opis:** API oparte na popularnym serialu animowanym „Rick i Morty”. Dostępne są informacje o postaciach, odcinkach i lokacjach.
- **URL endpointu:**
  ```
  https://rickandmortyapi.com/graphql
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    characters(page: 1, filter: { name: "Rick" }) {
      results {
        id
        name
        species
        gender
        status
      }
    }
  }
  ```

---

## 4. PokeAPI GraphQL (Pokemon)
- **Opis:** Udostępnia informacje o Pokemonach, ich typach, zdolnościach i statystykach.
- **URL endpointu:**
  ```
  https://graphql-pokemon2.vercel.app/
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    pokemon(name: "Pikachu") {
      id
      name
      types
      evolutions {
        id
        name
      }
    }
  }
  ```

---

## 5. Open Meteo GraphQL API (Weather Data)
- **Opis:** API dostarczające dane o prognozie pogody.
- **URL endpointu:**
  ```
  https://graphql-weather-api.herokuapp.com/
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    getCityByName(name: "London") {
      name
      weather {
        temperature {
          actual
          feelsLike
        }
        summary {
          description
        }
      }
    }
  }
  ```

---

## 6. FakeQL
- **Opis:** API umożliwiające generowanie fikcyjnych danych dla testów. Możesz dostosować dane do swoich potrzeb.
- **URL endpointu:**
  ```
  https://fakeql.com/graphql
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    users {
      id
      name
      email
    }
  }
  ```

---

## 7. Swapi GraphQL (Star Wars)
- **Opis:** API oparte na uniwersum Gwiezdnych Wojen. Udostępnia dane o postaciach, planetach, statkach kosmicznych i więcej.
- **URL endpointu:**
  ```
  https://swapi-graphql.netlify.app/.netlify/functions/index
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    allPeople {
      people {
        name
        gender
        homeworld {
          name
        }
      }
    }
  }
  ```

---

## 8. GraphQL Jobs API
- **Opis:** API udostępniające dane o ofertach pracy w technologii GraphQL.
- **URL endpointu:**
  ```
  https://api.graphql.jobs/
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    jobs {
      title
      company {
        name
        websiteUrl
      }
      cities {
        name
        country {
          name
        }
      }
    }
  }
  ```

---

## 9. Anime GraphQL API
- **Opis:** API dostarczające dane o anime, mangach i postaciach związanych z kulturą japońską.
- **URL endpointu:**
  ```
  https://graphql.anilist.co/
  ```
- **Przykładowe zapytanie:**
  ```graphql
  {
    Media(search: "Naruto") {
      id
      title {
        romaji
        english
        native
      }
      description
      episodes
    }
  }
  
