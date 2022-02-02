# Orderly
## Introduction
A order-status-checking web application built with Clojure and ClojureScript.

**Note**: *This is still work in progress. Check out the dev branch for the latest changes.*

## Motivation
I'm working in a small academic library. The staff here rely on Google Workspace (i.e. Gmail, Google Sheets, Google Drive) for everything in their daily routines.

One of my colleagues is responsible for placing book orders and supervising the acquisitions. She has to use several spreadsheets to track orders of different types, such as those for course reserves and those requested by faculty for their research.

She alone already starts feeling that the complexity of managing these sheets is ever increasing, not to mention that other team members also need to access the data spread over different places, for example, when answering patron's queries about a specific item on order.

It would be helpful if:
1. The information of acquisitions can be collected in a single spreadsheet, within which there might be a few sheets though;
2. Other colleagues just need to check order status from a single place, say, a website where all such info described in #1 is sync-ed over.

This project aims to solve the problem.

## Features
I use "features" because at the moment I can't think of any other better words.

- Built with Clojure(Script). Thus, no huge `node_modules` stuff
- Framework-less = Clojure way!

I use Selmer for templating for now, but I may likely switch to Hiccup in the future.

## Python
Since this project entails fetching data from Google Sheets, which in turn inevitably requires a library that wraps Google Apps APIs such as [Google Sheets API](https://developers.google.com/sheets/api/reference/rest). I have two options:
1. Use Python with the `gspread` library: <https://github.com/burnash/gspread>
2. Use Clojure with the `google-apps-clj` library: <https://github.com/SparkFund/google-apps-clj>

If I choose Python, I will need to rely on `psycopg` library to insert the data fetched from a specific sheet. I'm developing this project using a Apple Silicon MacBook Air (working remotely) and a Gentoo Linux PC (at my workplace). According to `psycopg` doc:
>At the time of writing we don’t distribute binary packages for Apple M1 (ARM) processors.

So I can't test database operations on my M1 machine, although I feel greately comfortable using Python to process data.

Clojure does not have this problem, but I need to figure out how to use the library since there are few doc.

At the time of writing, I'm still experimenting both possibilities.

## Shell
**NOTE**: The below method uses Google Spreadsheet API V3, but I prefer to use V4 instead. It is [google-apps-clj](https://github.com/SparkFund/google-apps-clj) that is used in this project.

The shell script is used to install the necessary dependencies for Clojure to be able to fetch data from Google Sheets. I found the solution in a blog: https://tech.metail.com/reading-google-sheets-from-clojure/ [Accessed: 2022-02-02]

I modified the shell script a bit to make it stick more to common shell code conventions.

## Roadmap
- [x] Set up server with http-kit
  - [x] create fns for starting and stopping server
  - [x] wrap the application with a dynamic reloading fn (dev only)
- [ ] Routes
  - [x] Three basic routes
  - [ ] About
  - [ ] Contact/Help
- [ ] Databse
  - [x] Set up a database using PostgresSQL
  - [x] Create \*.sql files containing SQL statements under sql/\*.sql
  - [x] Use `netx.jdbc` and `HugSQL` to create a table in the database
  - [x] Test `HugSQL`'s `def-sqlvec-fns` to see if SQL statements are right
  - [x] Use `gspread` to fetch data from Google Sheets
  - [ ] Properly store the fetched data for use by \*.clj files
- [ ] Search Engine
  - [ ] Search by Title, ISBN, Order Number, Author, etc,.
  - [ ] Search by ID in the database
