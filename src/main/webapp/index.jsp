<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Servlety - Rest API</title>
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<h2 class="h2 text-center mt-4 mb-4">Servlety - Rest API</h2>
<div class="col-8 offset-2">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Typ</th>
            <th scope="col">Endpoint</th>
            <th scope="col">Opis</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>POST</td>
            <td>/login</td>
            <td>Przyjmuje body o parametrach username i password. Jeśli dane są poprawne tworzony jest w sesji
                użytkownik oraz tworzone jest cookie dla użytkownika. W przeciwnymm razie zwracany jest komunikat o
                błędzie.
            </td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>GET</td>
            <td>/dashboard</td>
            <td>Zwraca aktualną listę książek</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>POST</td>
            <td>/dashboard</td>
            <td>Przyjmuje obiekt typu książka o parametrach title, author i year. Dodaje nowy obiekt do listy książek.
                Dostępne dla admina. Zwraca na wyjściu dodany obiekt.
            </td>
        </tr>
        <tr>
            <th scope="row">4</th>
            <td>DELETE</td>
            <td>/dashboard/{id}</td>
            <td>Usuwa książkę o podanym id. Dostępne dla admina. Zwraca na wyjściu usunięty obiekt.</td>
        </tr>
        <tr>
            <th scope="row">4</th>
            <td>GET</td>
            <td>/logout</td>
            <td>Wylogowuje aktualnie zalogowanego użytkownika</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
